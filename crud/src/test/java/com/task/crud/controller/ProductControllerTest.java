package com.task.crud.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.task.crud.exceptions.ProductNotFoundException;
import com.task.crud.model.Product;
import com.task.crud.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductControllerTest {

    public static final String EXCEPTION_MESSAGE = "Product not found with id: 1";
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        // Prepare a list of products to be returned by the productService
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product A", 100.0, "Description A"));
        products.add(new Product(2L, "Product B", 150.0, "Description B"));

        // Mock the productService's getAllProducts() method to return the prepared list
        when(productService.getAllProducts()).thenReturn(products);

        // Call the getAllProducts() method in the ProductController
        List<Product> result = productController.getAllProducts();

        // Assert that the result matches the prepared list of products
        assertThat(result).isEqualTo(products);

        // Verify that the productService's getAllProducts() method was called
        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }

    @Test
    void getProductById() {
        // Prepare a product to be returned by the productService
        Product product = new Product(1L, "Product A", 100.0, "Description A");

        // Mock the productService's getProductById() method to return the prepared product
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        // Call the getProductById() method in the ProductController
        Product result = productController.getProductById(1L);

        // Assert that the result matches the prepared product
        assertThat(result).isEqualTo(product);

        // Verify that the productService's getProductById() method was called
        verify(productService, times(1)).getProductById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void createProduct() {
        // Prepare a new product to be saved
        Product newProduct = new Product(null, "New Product", 99.99, "New Description");

        // Prepare the product with ID after being saved
        Product savedProduct = new Product(1L, "New Product", 99.99, "New Description");

        // Mock the productService's saveProduct() method to return the saved product
        when(productService.saveProduct(newProduct)).thenReturn(savedProduct);

        // Call the createProduct() method in the ProductController
        Product result = productController.createProduct(newProduct);

        // Assert that the result matches the saved product
        assertThat(result).isEqualTo(savedProduct);

        // Verify that the productService's saveProduct() method was called with the new product
        verify(productService, times(1)).saveProduct(newProduct);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void updateProduct() {
        // Prepare an existing product to be updated
        Product existingProduct = new Product(1L, "Existing Product", 50.0, "Existing Description");

        // Prepare the updated product with the same ID
        Product updatedProduct = new Product(1L, "Updated Product", 75.0, "Updated Description");

        // Mock the productService's saveProduct() method to return the updated product
        when(productService.saveProduct(updatedProduct)).thenReturn(updatedProduct);

        // Call the updateProduct() method in the ProductController
        Product result = productController.updateProduct(1L, updatedProduct);

        // Assert that the result matches the updated product
        assertThat(result).isEqualTo(updatedProduct);

        // Verify that the productService's saveProduct() method was called with the updated product
        verify(productService, times(1)).saveProduct(updatedProduct);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void deleteProduct() {
        // Call the deleteProduct() method in the ProductController
        productController.deleteProduct(1L);

        // Verify that the productService's deleteProduct() method was called with the correct ID
        verify(productService, times(1)).deleteProduct(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        // Mock the productService's getProductById() method to return an empty optional
        when(productService.getProductById(1L)).thenThrow(new ProductNotFoundException(EXCEPTION_MESSAGE));

        // Call the getProductById() method in the ProductController and expect ProductNotFoundException
        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> productController.getProductById(1L));

        // Verify that the productService's getProductById() method was called with the correct ID
        verify(productService, times(1)).getProductById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testHandleProductNotFoundException() {
        String exception = productController
                .handleProductNotFoundException(new ProductNotFoundException(EXCEPTION_MESSAGE));
        assertEquals(EXCEPTION_MESSAGE,exception);
    }
}