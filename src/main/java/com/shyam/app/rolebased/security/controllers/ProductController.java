package com.shyam.app.rolebased.security.controllers;

import com.shyam.app.rolebased.security.models.Product;
import com.shyam.app.rolebased.security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/name")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Product> getProductByName(@RequestParam("productName") String productName) {
        return ResponseEntity.ok(productService.getProductByName(productName));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }
}
