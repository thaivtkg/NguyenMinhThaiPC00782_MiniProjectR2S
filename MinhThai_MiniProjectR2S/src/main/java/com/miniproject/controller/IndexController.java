package com.miniproject.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mifmif.common.regex.Generex;
import com.miniproject.service.ProductService;

@Controller
public class IndexController {
	@Autowired
	ProductService productService;
	@GetMapping("index")
	public String index(Model m) {
		m.addAttribute("title", "Home");
		m.addAttribute("list", productService.findAll().stream().limit(6).collect(Collectors.toList()));
		return "home/index";
	}
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin(Model m) {
		m.addAttribute("title","Adminstrator");
		return "redirect:/admin/index.html";
	}
}
