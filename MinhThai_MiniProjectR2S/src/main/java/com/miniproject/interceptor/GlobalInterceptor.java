package com.miniproject.interceptor;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.miniproject.entity.Count;
import com.miniproject.service.BrandService;
import com.miniproject.service.ProductService;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	BrandService brandService;
	@Autowired
	ProductService productService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("brands", productService.CountByProduct());
		request.setAttribute("top3", productService.findAll().stream().limit(3).collect(Collectors.toList()));
	}
	
	
}
