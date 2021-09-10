package com.miniproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniproject.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") String id,Model m) {
		m.addAttribute("title","Order Detail");
		m.addAttribute("order",orderService.findById(id));
		return "order/detail";
	}
	@RequestMapping("/order/list")
	public String list(Model m,HttpServletRequest request) {
		m.addAttribute("title","Order List");
		String username=request.getRemoteUser();
		m.addAttribute("order",orderService.findByUsername(username));
		return "order/list";
	}
}
