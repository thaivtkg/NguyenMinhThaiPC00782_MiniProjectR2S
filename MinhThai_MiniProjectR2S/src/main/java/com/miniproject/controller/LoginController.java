package com.miniproject.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniproject.entity.Account;
import com.miniproject.service.AccountService;
import com.miniproject.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	@RequestMapping("auth/login/form")
	public String login(Model m, HttpServletRequest request) {
		m.addAttribute("title", "Login");
		if (request.getRemoteUser() != null) {
			return "redirect:/index";
		}
		return "account/login";
	}

	@RequestMapping("auth/login/success")
	public String loginSuccess(Model m,HttpServletRequest request) {
		m.addAttribute("title", "Login");
		m.addAttribute("message", "Đăng nhập thành công");
		if (request.getRemoteUser() != null) {
			return "redirect:/index";
		}
		return "account/login";
	}

	@RequestMapping("auth/login/error")
	public String loginError(Model m,HttpServletRequest req) {
		m.addAttribute("title", "Login");
		m.addAttribute("message", "Sai thông tin đăng nhập");
		return "account/login";
	}

	@RequestMapping("auth/signup")
	public String signup(Model m) {
		m.addAttribute("title", "Sign Up");
		return "account/signup";
	}
	@RequestMapping("auth/changepass")
	public String changepass(Model m) {
		m.addAttribute("title","Change PassWord");
		return "account/changepass";		
	}

	@RequestMapping("auth/logoff/success")
	public String logout(Model m) {
		return "account/login";
	}
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);		
		return "forward:/auth/login/success";
	}
}
