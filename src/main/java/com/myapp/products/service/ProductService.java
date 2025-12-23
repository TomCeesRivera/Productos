package com.myapp.products.service;

import com.myapp.products.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product findProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product increaseStock(Long id, Integer amount);

    Product decreaseStock(Long id, Integer amount);

    void deleteProduct(Long id);
}
