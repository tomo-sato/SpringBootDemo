package jp.dcworks.tsato.demo.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
 * リダイレクト+セッション利用の検証用コントローラ2.
 *
 * @author tomo-sato
 */
@Controller
@RequestMapping("/session2")
public class SessionTest2Controller {
	private static final Logger logger = LogManager.getLogger(SessionTest2Controller.class.getName());

	@Autowired
	private HttpSession session;


	@GetMapping("form")
	public String form(
			@ModelAttribute RequestForm requestForm
			, Model model) {
		logger.info("session2/form");

		// セッションデータクリア
		session.invalidate();
		// セッション処理中ステータスセット
		session.setAttribute("isSession", true);

		return "session2/form";
	}


	@PostMapping("regist")
	public String regist(
			@Validated @ModelAttribute RequestForm requestForm
			, BindingResult result
			, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("session2/regist");

		// セッションチェック
		Boolean isSession = (Boolean) session.getAttribute("isSession");
		if (!BooleanUtils.isTrue(isSession)) {
			logger.error("セッションエラー！！！");

			// 例外処理を入れる。
			throw new Exception("セッションエラー！！！");
		}

		// バリデーション
		if (result.hasErrors()) {
			logger.warn("バリデーションエラー");

			// バリデーション結果と、入力内容を、FlashScopeにセット。
			redirectAttributes.addFlashAttribute("errors", result);
			redirectAttributes.addFlashAttribute("requestForm", requestForm);

			// 「session2/form」にリダイレクト
			return "redirect:/session2/form";
		}

		// セッションクリア
		session.invalidate();

		// ※登録処理等、実装する。

		redirectAttributes.addFlashAttribute("isSuccess", "1");

		return "redirect:/";
	}
}
