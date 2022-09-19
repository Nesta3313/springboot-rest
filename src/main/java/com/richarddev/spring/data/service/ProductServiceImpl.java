package com.richarddev.spring.data.service;

import com.richarddev.spring.data.entity.ProductEntity;
import com.richarddev.spring.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

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
        LOGGER.info("Inside getProducts of product controller");
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(int id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        LOGGER.info("Inside getProductsById of product controller");
        return product.get();
    }

    @Override
    public List<ProductEntity> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity product, int id) {
        if(Objects.nonNull(product.getName()) &&
                !"".equalsIgnoreCase(product.getName())) {
            product.setName(product.getName());
        }

        if(Objects.nonNull(product.getQuantity())) {
            product.setQuantity(product.getQuantity());
        }

        if(Objects.nonNull(product.isAvailable())) {
            product.setAvailable(product.isAvailable());
        }

        LOGGER.info("Inside updateProduct of product controller");

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        LOGGER.info("Inside deleteProducts of product controller");
        productRepository.deleteById(id);

    }
}
