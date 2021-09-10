package com.miniproject.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.dao.AddressBookDAO;
import com.miniproject.entity.Address;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/address")
public class AddressBookRestController {
	@Autowired
	AddressBookDAO addressdao;
	
	
	@GetMapping("{username}")
	public List<String> getAddressByUser(@PathVariable("username") String username) {
		return addressdao.getAddressBook(username);
	}
	
	@PostMapping
	public Address create(@RequestBody Address address) {
		return addressdao.save(address);
	}
}
