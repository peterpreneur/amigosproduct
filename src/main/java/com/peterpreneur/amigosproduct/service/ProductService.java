package com.peterpreneur.amigosproduct.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.peterpreneur.amigosproduct.product.Product;
import com.peterpreneur.amigosproduct.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

/**
 * ProductService
 */
@Service 
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByNameAndStockLevel(String name, Integer stockLevel) {
        return productRepository.findByNameAndStockLevelOrderByCreatedAtAsc(name, stockLevel);
    }

}
