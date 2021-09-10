package com.miniproject.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mifmif.common.regex.Generex;
import com.miniproject.entity.Order;
import com.miniproject.entity.Report;
import com.miniproject.entity.TopSeller;
import com.miniproject.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public Order create(@Valid @RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	@GetMapping
	public List<Order> findAll(){
		return orderService.findAll();
	}
	@GetMapping("ProductRevenue")
	public List<Report> getProductRevenue(){
		return orderService.getRevenueByProduct();
	}
	@GetMapping("BrandRevenue")
	public List<Report> getBrandRevenue(){
		return orderService.getRevenueByBrand();
	}
	@GetMapping("TopSeller")
	public List<TopSeller> getTopseller(){
		return orderService.getTopSeller();
	}
}
