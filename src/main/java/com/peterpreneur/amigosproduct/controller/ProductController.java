/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peterpreneur.amigosproduct.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.peterpreneur.amigosproduct.product.Product;
import com.peterpreneur.amigosproduct.service.ProductService;

/**
 *
 * @author peter
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    private final JdbcTemplate jdbcTemplate;

    public ProductController(
            ProductService productService,
            JdbcTemplate jdbcTemplate) {
        this.productService = productService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable UUID id) {
        return productService.findProductById(id);
    }

    @GetMapping("/search")
    public List<Product> getProductsByName (@RequestParam(required= false) String name, @RequestParam (required=false) Integer stockLevel) {
        return productService.getProductsByNameAndStockLevel(name, stockLevel);
    }

    @GetMapping("/repo")
    public List<Product> getAllProductsRepo() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        Instant now = Instant.now();

        product.setId(UUID.randomUUID());
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        product.setDeletedAt(null);

        String sql = """
                INSERT INTO product (
                    id,
                    name,
                    description,
                    price,
                    image_url,
                    stock_level,
                    created_at,
                    updated_at,
                    deleted_at
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        int rowsInserted = jdbcTemplate.update(
                sql,
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getStockLevel(),
                Timestamp.from(product.getCreatedAt()),
                Timestamp.from(product.getUpdatedAt()),
                null);

        System.out.println("Rows inserted: " + rowsInserted);
        System.out.println(product.toString());

    }

    @GetMapping("/debug")
    public void printProducts() {
        String sql = "SELECT * FROM product";

        jdbcTemplate.query(sql, (rs, rowNum) -> {
            System.out.println(rs.getString("name"));
            return null;
        });
    }

    @GetMapping()
    public List<Product> getAllProducts() {

        String sql = """
                SELECT
                    id,
                    name,
                    description,
                    price,
                    image_url,
                    stock_level,
                    created_at,
                    updated_at,
                    deleted_at
                FROM product
                ORDER BY created_at DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToProduct(rs));

    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setId(rs.getObject("id", UUID.class));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setImageUrl(rs.getString("image_url"));
        product.setStockLevel(rs.getInt("stock_level"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        Timestamp deletedAt = rs.getTimestamp("deleted_at");

        product.setCreatedAt(createdAt != null ? createdAt.toInstant() : null);
        product.setUpdatedAt(updatedAt != null ? updatedAt.toInstant() : null);
        product.setDeletedAt(deletedAt != null ? deletedAt.toInstant() : null);

        return product;

    }

}
