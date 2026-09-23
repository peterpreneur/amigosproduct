package com.peterpreneur.amigosproduct.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peterpreneur.amigosproduct.product.Product;

@Repository 
public interface ProductRepository extends JpaRepository<Product, UUID> {

    

}
