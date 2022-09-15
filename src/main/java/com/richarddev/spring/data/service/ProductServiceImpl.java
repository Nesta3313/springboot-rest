package com.richarddev.spring.data.service;

import com.richarddev.spring.data.entity.ProductEntity;
import com.richarddev.spring.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(ProductEntity products) {
        return productRepository.save(products);

    }

    @Override
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    //fixme add controller for methods
    @Override
    public ProductEntity getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductEntity getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product, int id) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);

    }
}
