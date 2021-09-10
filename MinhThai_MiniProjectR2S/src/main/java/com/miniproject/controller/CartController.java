package com.miniproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniproject.dao.AddressBookDAO;

@Controller
public class CartController {
	@Autowired
	AddressBookDAO addressdao;
	@RequestMapping("cart/view")
	public String cart(Model m) {
		m.addAttribute("title","Cart");
		return "cart/cart-info";
	}
	@RequestMapping("cart/checkout")
	public String checkout(Model m,HttpServletRequest request) {
		m.addAttribute("title","Checkout");
		return "cart/checkout";
	}
}
