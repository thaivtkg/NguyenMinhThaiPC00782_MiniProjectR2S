package com.miniproject.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Accounts")
public class Account implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3293413410516438027L;
	@Id
	String username;
	String password;
	String fullname;
	String email;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	@JsonIgnore
	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
	List<Authority> authorities;
}
