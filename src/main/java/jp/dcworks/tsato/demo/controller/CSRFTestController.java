package jp.dcworks.tsato.demo.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.dcworks.tsato.demo.dto.RequestForm;
import lombok.extern.log4j.Log4j2;

/**
 * リダイレクト+セッション利用の検証用コントローラ2.
 *
 * @author tomo-sato
 */
@Log4j2
@Controller
@RequestMapping("/csrf")
public class CSRFTestController {

	@Autowired
	private HttpSession session;


	@GetMapping("form")
	public String form(
			@ModelAttribute RequestForm requestForm
			, Model model) {
		log.info("csrf/form");

		// セッション処理中ステータスセット
		session.setAttribute("isSession", true);

		return "csrf/form";
	}


	@PostMapping("regist")
	public String regist(
			@Validated @ModelAttribute RequestForm requestForm
			, BindingResult result
			, RedirectAttributes redirectAttributes) throws Exception {
		log.info("csrf/regist");

		// セッションチェック
		Boolean isSession = (Boolean) session.getAttribute("isSession");
		if (!BooleanUtils.isTrue(isSession)) {
			log.error("セッションエラー！！！");

			// 例外処理を入れる。
			throw new Exception("セッションエラー！！！");
		}

		// バリデーション
		if (result.hasErrors()) {
			log.warn("バリデーションエラー");

			// バリデーション結果と、入力内容を、FlashScopeにセット。
			redirectAttributes.addFlashAttribute("errors", result);
			redirectAttributes.addFlashAttribute("requestForm", requestForm);

			// 「csrf/form」にリダイレクト
			return "redirect:/csrf/form";
		}

		// セッションクリア
		session.removeAttribute("isSession");

		// ※登録処理等、実装する。

		redirectAttributes.addFlashAttribute("isSuccess", "1");

		return "redirect:/";
	}
}
