package com.miniproject.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.Count;
import com.miniproject.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p Where p.brand.id=?1")
	List<Product> findByBrandId(String id);

	@Query("SELECT p FROM Product p Where p.brand.id=?1")
	Page<Product> findByBrandId(String id, Pageable page);

	@Query("SELECT p FROM Product p Where p.available=true")
	Page<Product> findByAvailable(Pageable page);

	@Query("SELECT new com.miniproject.entity.Count(p.brand,COUNT(p.brand.id)) FROM Product p group by p.brand")
	List<Count> countByProduct();
	@Query("SELECT p FROM Product p where p.name=?1")
	Product findByProductName(String productname);

}
