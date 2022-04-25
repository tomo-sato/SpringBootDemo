package jp.dcworks.tsato.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.dcworks.tsato.demo.entity.User;
import jp.dcworks.tsato.demo.service.UserService;
import lombok.extern.log4j.Log4j2;

/**
 * データベース検証の為のコントローラ.
 *
 * @author tomo-sato
 */
@Log4j2
@Controller
@RequestMapping("/db")
public class DatabaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Pageable pageable, Model model) {
		log.info("index");

		Page<User> userPage = userService.findAll(pageable);
	    model.addAttribute("users", userPage);
	    model.addAttribute("page", userPage);
	    model.addAttribute("words", userPage.getContent());
	    model.addAttribute("url", "");

		return "db/index";
	}
}