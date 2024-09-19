package com.shyam.app.rolebased.security.services.Impl;

import com.shyam.app.rolebased.security.exceptions.ProductNotFoundException;
import com.shyam.app.rolebased.security.exceptions.ProductsNotFoundException;
import com.shyam.app.rolebased.security.models.Product;
import com.shyam.app.rolebased.security.repository.ProductRepository;
import com.shyam.app.rolebased.security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(Product product) {
        Product dbProduct = this.productRepository.findProductByName(product.getProductName());
        if(dbProduct==null){
            this.productRepository.save(product);
            return "Product added successfully";
        }else {
            dbProduct.setProductName(product.getProductName());
            dbProduct.setProductPrice(product.getProductPrice());
            dbProduct.setDepartment(product.getDepartment());
            this.productRepository.save(dbProduct);
            return "Product updated successfully";
        }
    }

    @Override
    public Product getProductByName(String productName) {
        Product dbProduct = this.productRepository.findProductByName(productName);
        if(dbProduct!=null){
            return dbProduct;
        }else{
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductsNotFoundException();
        }
        return products;
    }
}
