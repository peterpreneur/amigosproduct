/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.peterpreneur.amigosproduct.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author peter
 */
@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Integer stockLevel;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    @Override
    public String toString() {
        return "Product [" + System.lineSeparator()
                + "id=" + id + "," + System.lineSeparator()
                + "name=" + name + "," + System.lineSeparator()
                + "description=" + description + "," + System.lineSeparator()
                + "price=" + price + "," + System.lineSeparator()
                + "imageUrl=" + imageUrl + "," + System.lineSeparator()
                + "stockLevel=" + stockLevel + "," + System.lineSeparator()
                + "createdAt=" + createdAt + "," + System.lineSeparator()
                + "updatedAt=" + updatedAt + "," + System.lineSeparator()
                + "deletedAt=" + deletedAt + System.lineSeparator()
                + "]";
    }

}
