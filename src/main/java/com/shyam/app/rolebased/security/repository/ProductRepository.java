package com.shyam.app.rolebased.security.repository;

import com.shyam.app.rolebased.security.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    Product findProductByName(String productName);
}
