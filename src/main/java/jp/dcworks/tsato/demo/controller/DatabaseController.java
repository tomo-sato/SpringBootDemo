package jp.dcworks.tsato.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping(value="test2")
	public String getAllEmployees(
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			Model model) {

		Page<User> userPage = userService.findAll(pageNo, pageSize, sortBy);
		model.addAttribute("users", userPage);
		model.addAttribute("page", userPage);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("words", userPage.getContent());
		model.addAttribute("url", "");

		return "db/index2";
	}
}
