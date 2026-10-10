package com.supplychain.supply_chain_management.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supplychain.supply_chain_management.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> 
{
	
	List<Supplier> findByProduct_Name(String productName);

}
