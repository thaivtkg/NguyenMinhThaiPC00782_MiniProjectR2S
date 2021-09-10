package com.miniproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.dao.AccountDAO;
import com.miniproject.dao.AuthorityDAO;
import com.miniproject.entity.Account;
import com.miniproject.entity.Authority;
import com.miniproject.service.AuthorityService;



@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AccountDAO accdao;
	@Autowired
	AuthorityDAO dao;

	@Override
	public List<Authority> findAuthorititesOfAdministrators() {
		List<Account> accounts = accdao.getAdministrator();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {		
		return dao.findAll();
	}

	@Override
	public Authority create(Authority auth) {	// TODO Auto-generated method stub
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);		
	}

}
