package com.avenuecode.orders.repository;

import com.avenuecode.orders.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {

	@Query("SELECT p FROM Product p WHERE p.price > 30")
	List<Product> findProductsWithPriceMoreThan30();

}
