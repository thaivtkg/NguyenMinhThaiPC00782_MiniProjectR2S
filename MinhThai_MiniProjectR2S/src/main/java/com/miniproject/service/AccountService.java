package com.miniproject.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.miniproject.entity.Account;

public interface AccountService {
	Account findById(String username);
	
	Account create(JsonNode acc);
	
	List<Account> getAdministrators();
	
	Account create(Account acc);

	List<Account> findAll();
	
	void delete(String username);
	
}
