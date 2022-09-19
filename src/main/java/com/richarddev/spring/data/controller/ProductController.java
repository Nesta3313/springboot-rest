package com.richarddev.spring.data.controller;


import com.richarddev.spring.data.entity.ProductEntity;
import com.richarddev.spring.data.service.ProductService;
import com.richarddev.spring.data.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity saveProduct(@RequestBody ProductEntity products) {
        return productService.saveProduct(products);
    }

    @GetMapping("/products")
    public List<ProductEntity> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/name/{name}")
    public List<ProductEntity> getProductByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }


    @GetMapping("/products/{id}")
    public ProductEntity getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public ProductEntity updateProduct(@RequestBody ProductEntity product, @PathVariable int id) {
        return productService.updateProduct(product, id);
    }


    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return HttpStatus.NO_CONTENT;
    }
}
