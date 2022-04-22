package jp.dcworks.tsato.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
		// デフォルトのログイン画面を無効にする処理。
		http.authorizeRequests().anyRequest().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// デフォルトのユーザを削除してログに表示されないようにする。
		auth.inMemoryAuthentication();
	}
}
