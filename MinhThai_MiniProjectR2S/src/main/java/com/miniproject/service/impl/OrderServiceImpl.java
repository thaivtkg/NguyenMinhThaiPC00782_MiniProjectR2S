package com.miniproject.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import com.miniproject.dao.OrderDAO;
import com.miniproject.dao.OrderDetailDAO;
import com.miniproject.dao.ProductDAO;
import com.miniproject.entity.Order;
import com.miniproject.entity.OrderDetail;
import com.miniproject.entity.Product;
import com.miniproject.entity.Report;
import com.miniproject.entity.TopSeller;
import com.miniproject.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO ddao;
	@Autowired
	ProductDAO pdao;
	
	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper=new ObjectMapper();
		Order order=mapper.convertValue(orderData, Order.class);
		Generex generator=new Generex("[A-Z]{4}-[0-9]{5}");
		String randomcode=generator.random();
		order.setId(randomcode);
		dao.save(order);
		TypeReference<List<OrderDetail>> type=new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details=mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d->d.setOrder(order)).collect(Collectors.toList());
		details.forEach((i)->{
			Product product=pdao.findById(i.getProduct().getId()).get();
			product.setRemaining(product.getRemaining()-i.getQuantity());
			product.setAvailable(product.getRemaining()==0?false:true);
			pdao.save(product);
		});
		ddao.saveAll(details);
		return order;
	}

	@Override
	public Order findById(String id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<OrderDetail> findByProduct(Integer id) {
		// TODO Auto-generated method stub
		return ddao.findbyProduct(id);
	}

	@Override
	public List<Report> getRevenueByProduct() {
		// TODO Auto-generated method stub
		return ddao.getRevenueByProduct();
	}

	@Override
	public List<Report> getRevenueByBrand() {
		// TODO Auto-generated method stub
		return ddao.getRevenueByBrand();
	}

	@Override
	public List<TopSeller> getTopSeller() {
		// TODO Auto-generated method stub
		return ddao.getTopseller();
	}

	

}
