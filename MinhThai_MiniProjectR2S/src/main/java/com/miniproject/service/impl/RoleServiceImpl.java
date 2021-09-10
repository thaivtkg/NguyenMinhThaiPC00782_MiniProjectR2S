package com.miniproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.dao.RoleDAO;
import com.miniproject.entity.Role;
import com.miniproject.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO rdao;
	@Override
	public List<Role> findAll() {
		return rdao.findAll();
	}
	
}
