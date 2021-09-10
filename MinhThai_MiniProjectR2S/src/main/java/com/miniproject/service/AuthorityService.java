package com.miniproject.service;

import java.util.List;

import com.miniproject.entity.Authority;

public interface AuthorityService {

	List<Authority> findAuthorititesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority auth);


	void delete(Integer id);

}
