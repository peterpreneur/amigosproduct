/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peterpreneur.amigosproduct.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.peterpreneur.amigosproduct.product.Product;

/**
 *
 * @author peter
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final JdbcTemplate jdbcTemplate;

    public ProductController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
                null
        );

        System.out.println("Rows inserted: " + rowsInserted);
        System.out.println(product.toString());

    }

}
