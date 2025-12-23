package com.myapp.products.controller;

import com.myapp.products.model.Product;
import com.myapp.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> allProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id){
        return ResponseEntity.ok(service.findProduct(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.createProduct(product)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.updateProduct(id, product)
        );
    }

    @PatchMapping("/{id}/stock/increase")
    public ResponseEntity<Product> increaseStock(@PathVariable Long id, @RequestParam Integer amount){
        return ResponseEntity.ok(service.increaseStock(id, amount));
    }

    @PatchMapping("/{id}/stock/decrease")
    public ResponseEntity<Product> decreaseStock(@PathVariable Long id, @RequestParam Integer amount){
        return ResponseEntity.ok(service.decreaseStock(id, amount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
