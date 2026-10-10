package com.supplychain.supply_chain_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Supplier;
import com.supplychain.supply_chain_management.service.SupplierService;

@RestController
@RequestMapping("/suppliers")

public class SupplierController 
{
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(@RequestBody Supplier supplier)
	{
		return supplierService.saveSupplier(supplier);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier()
	{
		return supplierService.getAllSupplier();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(@PathVariable Integer id)
	{
		return supplierService.getSupplierById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier)
	{
		return supplierService.updateSupplier(supplier);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ResponseStructure<String>> deleteSupplier(@PathVariable Integer id)
	{
		return supplierService.deleteSupplier(id);
	}
	
	@GetMapping("/product/{productName}")
	public ResponseEntity<ResponseStructure<List<Supplier>>> getSupplierByProductName(@PathVariable String productName)
	{
		return supplierService.getSupplierByProductName(productName);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field)
	{
		return supplierService.getSupplierByPaginationAndSorting(pageNumber, pageSize, field);
	}




	
	

}
