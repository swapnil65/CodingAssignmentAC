/**
 * 
 */
package com.avenuecode.orders.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avenuecode.orders.domain.Product;
import com.avenuecode.orders.repository.ProductRepository;
import com.avenuecode.orders.service.ProductService;

/**
 * @author swapnilbalakrishna7
 *
 */
public class TestProductsService {

	private ProductService productService;
	private ProductRepository productRepositoryMock;
	private static final String productId = "3";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		productService = new ProductService();
		productRepositoryMock = mock(ProductRepository.class);
		productService.setProductRepository(productRepositoryMock);
	}

	@Test
	public void checkListProducts() {
		List<Product> expected = new ArrayList<Product>();
		when(productRepositoryMock.findAll()).thenReturn(expected);
		List<Product> actual = productService.listProducts();
		verify(productRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(productRepositoryMock);
		assertEquals(expected, actual);
	}

	@Test
	public void checkGetProduct() {
		Product expected = new Product();
		when(productRepositoryMock.findOne(productId)).thenReturn(expected);
		Product actual = productService.getProduct(productId);
		verify(productRepositoryMock, times(1)).findOne(productId);
		verifyNoMoreInteractions(productRepositoryMock);
		assertEquals(expected, actual);
	}

	@Test
	public void checkGetProductsWithPriceMoreThan30() {
		List<Product> expected = new ArrayList<Product>();
		when(productRepositoryMock.findProductsWithPriceMoreThan30())
				.thenReturn(expected);
		List<Product> actual = productService.getProductsWithPriceMoreThan30();
		verify(productRepositoryMock, times(1))
				.findProductsWithPriceMoreThan30();
		verifyNoMoreInteractions(productRepositoryMock);
		assertEquals(expected, actual);
	}

}
