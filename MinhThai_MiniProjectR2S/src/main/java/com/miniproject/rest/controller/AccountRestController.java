package com.miniproject.rest.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.miniproject.entity.Account;
import com.miniproject.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@PostMapping
	public Account create(@RequestBody Account data) {
		return accountService.create(data);
	}
	@GetMapping
	public List<Account> getAccounts(){
		return accountService.findAll();
	}
	@GetMapping("/user")
	public Account getAccount(HttpServletRequest req) {
		String id=req.getRemoteUser();
		return accountService.findById(id);	
	}
	@PutMapping("/changepass")
	public Account update(@RequestBody Account data) {
		return accountService.create(data);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")String username) {
		accountService.delete(username);
	}
	@PutMapping("{id}")
	public void delete(@PathVariable("id")String id,@RequestBody Account acc) {
		accountService.create(acc);
	}
}
