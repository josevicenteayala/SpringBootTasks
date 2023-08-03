package com.task.crud.service;

import com.task.crud.exceptions.ProductNotFoundException;
import com.task.crud.model.Product;
import com.task.crud.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        Optional<Product> productRepositoryById = productRepository.findById(id);
        return Optional.of(productRepositoryById
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id)));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
