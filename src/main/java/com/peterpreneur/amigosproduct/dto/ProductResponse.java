/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.peterpreneur.amigosproduct.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author peter
 */
public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        Integer stockLevel,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

}
