package jp.dcworks.tsato.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * テンプレートで使いたいメソッドを定義.
 *
 * @author tomo-sato
 * @see <a href="https://kojion.com/posts/812">コジオニルク - Thymeleaf の機能で賄いきれないコードを実行したい</a>
 */
@Log4j2
@Component
public class TestUtil {

	/** セッション情報. */
	@Autowired
	private HttpSession session;

	public static boolean isTest() {
		return true;
	}

	public static boolean isTest(String test) {
		log.info(test);
		return true;
	}

	public static boolean isTest(String test1, String test2) {
		log.info(test1 + ":" + test2);
		return true;
	}

	public boolean isTest2() {
		log.info(session.getAttribute("hogeSession"));
		return true;
	}

	public boolean isTest2(String test) {
		log.info(session.getAttribute("hogeSession"));
		log.info(test);
		return true;
	}

	public boolean isTest2(String test1, String test2) {
		log.info(session.getAttribute("hogeSession"));
		log.info(test1 + ":" + test2);
		return true;
	}

	public static void main(String[] args) {
		log.info("hogehoge");
	}
}
