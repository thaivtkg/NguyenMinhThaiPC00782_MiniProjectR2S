package com.miniproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.OrderDetail;
import com.miniproject.entity.Report;
import com.miniproject.entity.TopSeller;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
	@Query("Select o from OrderDetail o where o.product.id=?1")
	List<OrderDetail> findbyProduct(Integer id);
	
	@Query("Select new com.miniproject.entity.Report(d.product.brand, sum(d.price * d.quantity)"
			+ ",sum(d.quantity)) from OrderDetail d GROUP BY d.product.brand")
	List<Report> getRevenueByBrand();

	@Query("Select new com.miniproject.entity.Report(d.product, sum(d.price * d.quantity)"
			+ ",sum(d.quantity)) from OrderDetail d GROUP BY d.product")
	List<Report> getRevenueByProduct();
	
	@Query("Select new com.miniproject.entity.TopSeller(d.product, d.product.brand"
			+ ",sum(d.quantity)) from OrderDetail d GROUP BY d.product,d.product.brand having sum(d.quantity) >2")
	List<TopSeller> getTopseller();
}
