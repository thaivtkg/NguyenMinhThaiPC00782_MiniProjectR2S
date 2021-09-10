package com.miniproject.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.miniproject.entity.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TopSeller {
	@Id
	private Serializable group;
	private Brand brand;
	private long quantity;
}
