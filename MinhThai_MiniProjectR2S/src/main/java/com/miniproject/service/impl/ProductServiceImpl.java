package com.miniproject.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.miniproject.dao.ProductDAO;
import com.miniproject.entity.Count;
import com.miniproject.entity.Product;
import com.miniproject.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO pdao;

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pdao.findById(id).get();
	}

	@Override
	public Product getById(Integer id) {
		return pdao.getById(id);
	}

	@Override
	public List<Product> findByBrandId(String cid) {
		return pdao.findByBrandId(cid);
	}

	@Override
	public List<Count> CountByProduct() {
		return pdao.countByProduct();
	}

	@Override
	public Page<Product> findAll(Pageable page) {
		return pdao.findAll(page);
	}

	@Override
	public Page<Product> findByBrandId(String id, Pageable page) {
		return pdao.findByBrandId(id, page);
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) { // TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
		pdao.deleteById(id);
	}

	@Override
	public Page<Product> findAllAvailable(Pageable page) { // TODO Auto-generated method stub
		return pdao.findByAvailable(page);
	}

	@Override
	public Product findByProductName(String productname) {
		return pdao.findByProductName(productname);
	}

}
