package com.miniproject.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN('ADMIN','USER')")
	List<Account> getAdministrator();
}
