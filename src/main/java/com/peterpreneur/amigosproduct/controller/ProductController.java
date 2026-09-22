/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peterpreneur.amigosproduct.controller;

import java.time.Instant;
import java.util.UUID;

import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        Instant now = Instant.now();

        product.setId(UUID.randomUUID());
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        product.setDeletedAt(now);

        System.out.println(product.toString());

    }

}
