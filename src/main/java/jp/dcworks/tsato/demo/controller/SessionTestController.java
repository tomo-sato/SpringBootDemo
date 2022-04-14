package jp.dcworks.tsato.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.dcworks.tsato.demo.dto.RequestForm;

/**
 * リダイレクト+セッション利用の検証用コントローラ.
 *
 * @author tomo-sato
 */
@Controller
@RequestMapping("/session")
@SessionAttributes(value = {"requestForm"})
public class SessionTestController {
	private static final Logger logger = LogManager.getLogger(SessionTestController.class.getName());

	@ModelAttribute(value = "requestForm")
    public RequestForm setUpRequestForm() {
		logger.info("setUpRequestForm");
        return new RequestForm();
    }

	@GetMapping("form")
	public String form(
			@ModelAttribute("requestForm") RequestForm requestForm
			, Model model) {
		logger.info("session/form");

		model.addAttribute(requestForm);

		return "session/form";
	}


	@PostMapping("regist")
	public String regist(
			@Validated @ModelAttribute("requestForm") RequestForm requestForm
			, BindingResult result
			, RedirectAttributes redirectAttributes
			, SessionStatus sessionStatus) {
		logger.info("session/regist");

		if (requestForm == null) {
			logger.info("requestFormは破棄されている。");
		}

		// バリデーション
		if (result.hasErrors()) {
			logger.warn("バリデーションエラー");

			// バリデーション結果と、入力内容を、FlashScopeにセット。
			redirectAttributes.addFlashAttribute("errors", result);
			redirectAttributes.addFlashAttribute("requestForm", requestForm);

			// セッション破棄
			sessionStatus.setComplete();

			// 「session/form」にリダイレクト
			return "redirect:/session/form";
		}

		redirectAttributes.addFlashAttribute("isSuccess", "1");

		// セッション破棄
		sessionStatus.setComplete();

		return "redirect:/";
	}
}
