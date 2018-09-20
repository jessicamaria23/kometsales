package com.elcafetal.elcafetal.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.elcafetal.elcafetal.models.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {	
}