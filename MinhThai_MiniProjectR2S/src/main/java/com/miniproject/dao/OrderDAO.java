package com.miniproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.Order;

public interface OrderDAO extends JpaRepository<Order, String> {
	@Query("SELECT o FROM Order o where o.account.username=?1")
	List<Order> findByUsername(String username);

}
