package com.shyam.app.rolebased.security.services;

import com.shyam.app.rolebased.security.models.Product;

import java.util.List;

public interface ProductService {

    String addProduct(Product product);
    Product getProductByName(String productName);
    List<Product> getAllProducts();
}
