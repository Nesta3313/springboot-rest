package com.richarddev.spring.data.service;

import com.richarddev.spring.data.entity.ProductEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;


public interface ProductService {
    ProductEntity saveProduct(ProductEntity products);
    List<ProductEntity> getProducts();
    ProductEntity getProductById(int id);
    List<ProductEntity> getProductsByName(String name);
    ProductEntity updateProduct(ProductEntity product, int id);
    void deleteProduct(int id);




}
