package com.miniproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.Address;

public interface AddressBookDAO extends JpaRepository<Address, Integer> {
	@Query("SELECT DISTINCT add.address FROM Address add WHERE add.account.username=?1")
	List<String> getAddressBook(String username);
}
