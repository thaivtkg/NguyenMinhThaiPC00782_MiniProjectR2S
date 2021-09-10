package com.miniproject.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5555458689394138867L;
	@Id
	String id;
	String name;
	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	List<Product> products;	
}
