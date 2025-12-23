package com.myapp.products.service;

import com.myapp.products.exception.ProductNotFoundException;
import com.myapp.products.model.Product;
import com.myapp.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Producto no encontrado"));
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if(product.getStock() != null){
            throw new IllegalArgumentException("No se puede modificar el stock");
        }
        Product updateProduct = repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Producto no encontrado"));
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());

        return repository.save(updateProduct);
    }

    @Override
    public Product increaseStock(Long id, Integer amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        Product product = repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Producto no encontrado"));
        product.setStock(product.getStock() + amount);

        return repository.save(product);
    }

    @Override
    public Product decreaseStock(Long id, Integer amount) {
        if(amount <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        Product product = repository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Producto no encontrado"));

        if(product.getStock() < amount){
            throw new IllegalArgumentException("Stock insuficiente");
        }
        product.setStock(product.getStock() - amount);

        return repository.save(product);
    }


    @Override
    public void deleteProduct(Long id) {
        if(!repository.existsById(id)){
            throw new ProductNotFoundException("No se encuentra el producto a eliminar");
        }
        repository.deleteById(id);
    }
}
