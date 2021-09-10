package com.miniproject.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table
public class Role  implements Serializable{
	@Id
	private String id="GUEST";
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> authorities;
}