package jp.dcworks.tsato.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.dcworks.tsato.demo.dto.RequestForm;

/**
 * リダイレクト検証の為のコントローラ.
 *
 * @author tomo-sato
 */
@Controller
@RequestMapping("/")
public class RedirectTestController {
	private static final Logger logger = LogManager.getLogger(RedirectTestController.class.getName());

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {
		logger.info("index");

		return "index";
	}


	@GetMapping("test/form")
	public String form(
			@ModelAttribute RequestForm requestForm) {
		logger.info("test/form");

		return "test/form";
	}


	@PostMapping("test/regist")
	public String regist(
			@Validated @ModelAttribute RequestForm requestForm
			, BindingResult result
			, RedirectAttributes redirectAttributes) {
		logger.info("test/regist");

		// バリデーション
		if (result.hasErrors()) {
			logger.warn("バリデーションエラー");

			// バリデーション結果と、入力内容を、FlashScopeにセット。
			redirectAttributes.addFlashAttribute("errors", result);
			redirectAttributes.addFlashAttribute("requestForm", requestForm);

			// 「test/form」にリダイレクト
			return "redirect:/test/form";
		}

		redirectAttributes.addFlashAttribute("isSuccess", "1");
		return "redirect:/";
	}
}
