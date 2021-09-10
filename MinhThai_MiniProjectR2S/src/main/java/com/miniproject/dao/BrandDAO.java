package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.entity.Brand;

public interface BrandDAO extends JpaRepository<Brand, String> {

}
