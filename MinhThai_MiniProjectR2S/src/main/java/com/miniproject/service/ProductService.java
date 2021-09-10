package com.miniproject.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miniproject.entity.Count;
import com.miniproject.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);
	
	Product getById(Integer id);
	
	List<Product> findByBrandId(String string);

	List<Count> CountByProduct();

	Page<Product> findAll(Pageable page);

	Page<Product> findByBrandId(String id, Pageable page);

	Product create(@Valid Product product);
	
	Product update(Product product);

	void delete(Integer id);

	Page<Product> findAllAvailable(Pageable page);
	
	Product findByProductName(String productname);
}
