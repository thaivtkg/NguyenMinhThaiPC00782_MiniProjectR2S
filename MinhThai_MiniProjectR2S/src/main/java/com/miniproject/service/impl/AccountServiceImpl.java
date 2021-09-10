package com.miniproject.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniproject.dao.AccountDAO;
import com.miniproject.dao.AuthorityDAO;
import com.miniproject.entity.Account;
import com.miniproject.entity.Authority;
import com.miniproject.entity.Role;
import com.miniproject.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO dao;
	@Autowired
	AuthorityDAO audao;
	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}
	@Override
	public Account create(JsonNode acc) {
		ObjectMapper mapper=new ObjectMapper();
		Account account=mapper.convertValue(acc, Account.class);
		dao.save(account);
		Authority auth=new Authority();
		Role role=new Role();
		auth.setAccount(account);
		auth.setRole(role);
		audao.save(auth);
		return account;
	}
	@Override
	public List<Account> getAdministrators() {
		return dao.getAdministrator();
	}
	@Override
	public Account create(Account acc) {		
		return dao.save(acc);
	}
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public void delete(String username) {
		dao.deleteById(username);		
	}
	
	
	
}
