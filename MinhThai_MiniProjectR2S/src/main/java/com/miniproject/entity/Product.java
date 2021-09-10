package com.miniproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Products")
@ApiModel("Product model")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NotEmpty(message = "Không được để trống")
	String name;
	String image;
	@NotNull(message = "Không được để trống giá")
	@Min(value = 1000000,message = "Giá phải lớn hơn 1 triệu")
	Double price;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate=new Date();
	Boolean available;
	Integer remaining=100;
	@NotNull(message = "Vui lòng chọn hãng")
	@ManyToOne @JoinColumn(name = "Brandid")
	Brand brand;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderdetails;
}
