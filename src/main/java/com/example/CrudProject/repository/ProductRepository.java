package com.example.CrudProject.repository;

import com.example.CrudProject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
    Optional<Product> findByName(String name);
}
