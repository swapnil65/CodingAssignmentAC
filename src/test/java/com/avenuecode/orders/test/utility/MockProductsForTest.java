package com.avenuecode.orders.test.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.avenuecode.orders.domain.Product;

public class MockProductsForTest {
	
	public Product getSingleProduct(){
		Product product = new Product("2","1358743283","7394650110003","Polo Shirt",new BigDecimal(19.99));
		return product;
	}
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product("1","1257833283","9394550220002","Diva Jeans",new BigDecimal(39.99));
		Product product2 = new Product("2","1358743283","7394650110003","Polo Shirt",new BigDecimal(19.99));
		Product product3 = new Product("3", "1458843283", "7394750120000", "Floral Swing Skirt", new BigDecimal(69.99));
		Product product4 = new Product("4", "1358753283", "7394850130001", "Denim Short", new BigDecimal(69.99));
		Product product5 = new Product("5", "1258793283", "7394950140000", "True Skinny Jeans",new BigDecimal(29.99));
		products.add(product1);
		products.add(product2);
		products.add(product3);	
		products.add(product4);	
		products.add(product5);	
		return products;
	}
	
	public List<Product> getProductsMoreThan30() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product("1","1257833283","9394550220002","Diva Jeans",new BigDecimal(39.99));
		Product product2 = new Product("3", "1458843283", "7394750120000", "Floral Swing Skirt", new BigDecimal(69.99));
		Product product3 = new Product("5", "1258793283", "7394950140000", "True Skinny Jeans",new BigDecimal(29.99));
		products.add(product1);
		products.add(product2);
		products.add(product3);
		return products;	
	}

}
