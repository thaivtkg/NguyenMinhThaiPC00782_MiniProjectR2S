package com.miniproject.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.entity.Count;
import com.miniproject.entity.Product;
import com.miniproject.service.OrderService;
import com.miniproject.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;

	@ApiOperation(value = "Tìm sản phẩm theo Id", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Thành công"),
			@ApiResponse(code = 401, message = "Chưa xác thực"), @ApiResponse(code = 403, message = "Truy cập bị cấm"),
			@ApiResponse(code = 404, message = "Không tìm thấy") })
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}

	@ApiOperation(value = "Danh sách sản phẩm")
	@GetMapping
	public List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("count")
	public List<Count> count() {
		return productService.CountByProduct();
	}

	@GetMapping("/brand/{brandid}")
	@ApiOperation(value = "Tìm sản phẩm theo hãng")
	public List<Product> getOne(@PathVariable("brandid") String id) {
		return productService.findByBrandId(id);
	}

	@ApiOperation(value = "Thêm sản phẩm")
	@PostMapping
	public Product create(@Valid @RequestBody Product product) {
		return productService.create(product);
	}

	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
		return productService.update(product);
	}

	@ApiOperation(value = "Xóa sản phẩm")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if (!orderService.findByProduct(id).isEmpty()) {
			return ResponseEntity.status(405).build();
		} else {
			productService.delete(id);
		}
		return ResponseEntity.ok().build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
