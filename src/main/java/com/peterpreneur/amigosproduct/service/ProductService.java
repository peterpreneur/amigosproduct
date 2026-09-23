package com.peterpreneur.amigosproduct.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.peterpreneur.amigosproduct.dto.NewProductRequest;
import com.peterpreneur.amigosproduct.dto.ProductResponse;
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

    public List<ProductResponse> getAllProductsFromRequest() {
        return productRepository.findAll().stream()
                .map(mapToResponse())
                .collect(Collectors.toList());
    }

    private Function<Product, ProductResponse> mapToResponse() {
        return p -> new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getImageUrl(),
                p.getStockLevel(),
                p.getCreatedAt(),
                p.getUpdatedAt(),
                p.getDeletedAt());
    }

    public Optional<ProductResponse> findProductById(UUID id) {
        return productRepository.findById(id).map(mapToResponse());
    }

    public List<ProductResponse> getProductsByNameAndStockLevel(String name, Integer stockLevel) {
        return productRepository.findByNameAndStockLevelOrderByCreatedAtAsc(name, stockLevel)
        .stream()
        .map(mapToResponse())
        .collect(Collectors.toList());
    }

    public UUID saveNewProduct(NewProductRequest product) {
        UUID id = UUID.randomUUID();
        Product p = new Product();
        p.setId(id);
        p.setName(product.name());
        p.setDescription(product.description());
        p.setPrice(product.price());
        p.setStockLevel(product.stockLevel());
        p.setImageUrl(product.imageUrl());
        productRepository.save(p);
        return id;
    }

}
