package com.miniproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.dao.BrandDAO;
import com.miniproject.entity.Brand;
import com.miniproject.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandDAO bdao;

	@Override
	public List<Brand> findAll() {
		return bdao.findAll();
	}

}
