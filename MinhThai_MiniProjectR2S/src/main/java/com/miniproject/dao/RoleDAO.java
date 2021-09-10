package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {

}
