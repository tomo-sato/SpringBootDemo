package jp.dcworks.tsato.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * リダイレクト検証の為のコントローラ.
 *
 * @author tomo-sato
 */
@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = LogManager.getLogger(IndexController.class.getName());

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {
		logger.info("index");

		return "index";
	}
}
