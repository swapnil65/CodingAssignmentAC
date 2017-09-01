package com.avenuecode.orders.service;

import com.avenuecode.orders.domain.Product;
import com.avenuecode.orders.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> listProducts() {
		return productRepository.findAll();
	}

	public Product getProduct(String productId) {
		return productRepository.findOne(productId);
	}

	public List<Product> getProductsWithPriceMoreThan30() {
		return productRepository.findProductsWithPriceMoreThan30();
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
