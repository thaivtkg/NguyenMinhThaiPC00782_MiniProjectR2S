package com.miniproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.Account;
import com.miniproject.entity.Authority;
import com.miniproject.entity.Role;

public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
	@Query("SELECT auth.role FROM Authority auth WHERE auth.account.username=?1")
	public List<Role> findRoleByUsername(String username);
	
	@Query("Select DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
}
