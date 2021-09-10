package com.miniproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miniproject.entity.Product;
import com.miniproject.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@RequestMapping("product/detail/{id}")
	public String detail(@PathVariable("id") Integer id,Model m) {
		Product product=productService.findById(id);
		m.addAttribute("title", product.getName());
		m.addAttribute("product", product);
		m.addAttribute("list",productService.findByBrandId(product.getBrand().getId()));
		return "product/detail";
	}

	@RequestMapping("product/list")
	public String list(Model m, @RequestParam("brand") Optional<String> brand,
			@RequestParam("page") Optional<Integer> pageNum) {
		m.addAttribute("title", "Shop");		
		if (brand.isPresent()) {
			Pageable page = PageRequest.of(pageNum.orElse(0),6);
			Page<Product> list = productService.findByBrandId(brand.get(), page);
			m.addAttribute("products", list.getContent());
			m.addAttribute("currentPage", pageNum.orElse(0));
			m.addAttribute("totalPages", list.getTotalPages());
			m.addAttribute("totalItems", list.getTotalElements());
			m.addAttribute("brand",brand.orElse(""));
		} else {
			Pageable page = PageRequest.of(pageNum.orElse(0), 6);
			Page<Product> list = productService.findAllAvailable(page);
			m.addAttribute("products", list.getContent());
			m.addAttribute("currentPage", pageNum.orElse(0));
			m.addAttribute("totalPages", list.getTotalPages());
			m.addAttribute("totalItems", list.getTotalElements());
			m.addAttribute("brand",brand.orElse(""));
		}

		return "product/list";
	}
}
