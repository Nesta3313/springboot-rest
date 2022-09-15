package com.richarddev.spring.data.service;

import com.richarddev.spring.data.entity.ProductEntity;
import com.richarddev.spring.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @Mock
    private ProductRepository repository;

    @Autowired
    @InjectMocks
    private ProductServiceImpl productService;
    private ProductEntity sut;
    private List<ProductEntity> productList;


    @BeforeEach
    private void setup() {
        sut = ProductEntity.builder()
                .product_id(22)
                .name("Toyota")
                .isAvailable(true)
                .quantity(400)
                .build();
    }

    @Test
    @DisplayName("Product is saved when method is called")
    public void productIsSavedWhenMethodIsCalled() {
        given(repository.save(sut)).willReturn(sut);
        ProductEntity savedProduct = productService.saveProduct(sut);
        assertThat(savedProduct).isNotNull();
        System.out.println(repository);
        System.out.println(productService);
//        when(repository.save(any())).thenReturn(sut);
//        productService.saveProduct(sut);
//        verify(repository, times(1)).save(any());


    }

    @Test
    @DisplayName("Given product exist should return product")
    public void givenProductExistReturnProduct() {
        ProductEntity sut2 = ProductEntity.builder()
                .product_id(10)
                .name("Lexus")
                .isAvailable(true)
                .quantity(210)
                .build();
        given(repository.findAll()).willReturn(List.of(sut, sut2));

        List<ProductEntity> productList = productService.getProducts();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("GIven product exist should return product by Id")
    public void givenProductExistShouldReturnProductById() {

        given(repository.findById(anyInt())).willReturn(Optional.ofNullable(sut));


        ProductEntity product = productService.getProductById(sut.getProduct_id());

        assertThat(product.getName()).isEqualTo("Toyota");
        assertThat(product.getQuantity()).isEqualTo(400);
    }

    @Test
    @DisplayName("GIven product exist should return product by name")
    public void givenProductExistShouldReturnProductByName() {
        given(repository.findByName(anyString())).willReturn(sut);


        ProductEntity product = productService.getProductByName(sut.getName());

        assertThat(product.getName()).isEqualTo("Toyota");
        assertThat(product.getQuantity()).isEqualTo(400);
        assertThat(product.isAvailable()).isEqualTo(true);

    }

    @Test
    @DisplayName("Given product exist should update product")
    public void givenProductExistShouldUpdateProduct() {
        sut.setName("Samsung");
        sut.setAvailable(true);
        sut.setQuantity(390);
        when(repository.save(sut)).thenReturn(sut);

        ProductEntity newSut = repository.save(sut);

        assertThat(newSut.getName()).isEqualTo("Samsung");
        assertThat(newSut.getQuantity()).isEqualTo(390);
        verify(repository, times(1)).save(sut);



    }

    @Test
    @DisplayName("Given product exist should delete product")
    public void givenProductExistShouldDeleteProduct() {

        willDoNothing().given(repository).deleteById(sut.getProduct_id());

        productService.deleteProduct(sut.getProduct_id());

        verify(repository, times(1)).deleteById(sut.getProduct_id());
    }



}