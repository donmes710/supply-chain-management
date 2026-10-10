package com.supplychain.supply_chain_management.repository;

import com.supplychain.supply_chain_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findBySupplierId(int supplierId);
    
    List<Product> findByIsActiveTrue();
    
    Optional<Product> findByIdAndIsActiveIsTrue(Integer id);
}