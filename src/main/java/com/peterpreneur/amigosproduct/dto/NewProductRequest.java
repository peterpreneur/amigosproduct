/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.peterpreneur.amigosproduct.dto;

import java.math.BigDecimal;

/**
 *
 * @author peter
 */
public record NewProductRequest(
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        Integer stockLevel) {

}
