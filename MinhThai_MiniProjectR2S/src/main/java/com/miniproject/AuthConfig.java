package com.miniproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import com.miniproject.service.AccountService;
import com.miniproject.service.UserService;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
		
		http.authorizeRequests().antMatchers("/cart/checkout","/order/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("ADMIN","USER").antMatchers("/rest/authority").hasRole("ADMIN")
		.anyRequest().permitAll();

		http.formLogin().loginPage("/auth/login/form").loginProcessingUrl("/auth/login")
				.defaultSuccessUrl("/index", false).failureUrl("/auth/login/error").usernameParameter("username")
				.passwordParameter("password");
		
		http.logout().logoutUrl("/auth/logoff")
		.logoutSuccessUrl("/index");
		//Đăng nhập mạng xã hội
		http.oauth2Login()
		.loginPage("/auth/login/form")
		.defaultSuccessUrl("/oauth2/login/success",false)
		.failureUrl("/auth/login/error")
		.authorizationEndpoint()
		.baseUri("/oauth2/authorization")
		.authorizationRequestRepository(getRepository())
		.and().tokenEndpoint()
		.accessTokenResponseClient(getToken());
	}
	@Bean
	public AuthorizationRequestRepository<OAuth2AuthorizationRequest> getRepository(){
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}
	@Bean
	public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> getToken(){
		return new DefaultAuthorizationCodeTokenResponseClient();
	}
}
