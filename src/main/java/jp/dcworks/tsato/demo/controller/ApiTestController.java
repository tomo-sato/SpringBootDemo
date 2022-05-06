package jp.dcworks.tsato.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * REST API検証の為のコントローラ.
 *
 * @author tomo-sato
 */
@Log4j2
@RestController
@RequestMapping("/api1")
public class ApiTestController {

	@Data
	public static class Hoge {
		private String name;
		private Integer age;

		public Hoge() {}

		public Hoge(String name, Integer age) {
			this.name = name;
			this.age = age;
		}
	}

	/**
	 * 検証要API
	 * @return
	 */
	@GetMapping("test")
	public Hoge test() {
		log.info("test");

		return new Hoge("aaa", 31);
	}
}
