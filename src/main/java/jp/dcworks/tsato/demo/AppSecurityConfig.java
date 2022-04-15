package jp.dcworks.tsato.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 参考サイト：
 * 	Spring BootでWebセキュリティを設定しよう (1/2)：CodeZine（コードジン）
 * 	https://codezine.jp/article/detail/11703
 *
 * @author tomo-sato
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();

		// デフォルトのsession保持だと意図しない挙動になる。一旦Cookieで検証。
		http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
	}
}
