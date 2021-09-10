package com.miniproject.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.miniproject.entity.Order;
import com.miniproject.entity.OrderDetail;
import com.miniproject.entity.Report;
import com.miniproject.entity.TopSeller;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(String id);

	List<Order> findByUsername(String username);

	List<Order> findAll();

	List<OrderDetail> findByProduct(Integer id);

	List<Report> getRevenueByProduct();

	List<Report> getRevenueByBrand();
	
	List<TopSeller> getTopSeller();
}
