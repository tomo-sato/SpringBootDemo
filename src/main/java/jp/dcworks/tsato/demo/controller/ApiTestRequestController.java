package jp.dcworks.tsato.demo.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

/**
 * REST API検証の為のコントローラ.
 *
 * @author tomo-sato
 */
@Log4j2
@Controller
@RequestMapping("/api2")
public class ApiTestRequestController {

	@GetMapping("")
	public String index(Model model) {

		// RestTemplate を使ってみる。
		ApiTestController.Hoge hoge = restTemplate().getForObject("http://localhost:8080/api1/test", ApiTestController.Hoge.class);
		log.info(hoge);

		return "api/index";
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.build();
	}
}
