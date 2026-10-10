package com.supplychain.supply_chain_management.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.supplychain.supply_chain_management.entity.Supplier;
import com.supplychain.supply_chain_management.repository.SupplierRepository;

@Repository
public class SupplierDao 
{
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier saveSupplier(Supplier supplier)
	{
		return supplierRepository.save(supplier);
	}
	
	public List<Supplier> getAllSupplier()
	{
		return supplierRepository.findAll();
	}
	
	public Optional<Supplier> getSupplierById(Integer id)
	{
		return supplierRepository.findById(id);
	}
	
	public void deleteSupplier(Supplier supplier)
	{
		supplierRepository.delete(supplier);
	}

	public  List<Supplier> getSupplierByProductName(String productName)
	{
		return supplierRepository.findByProduct_Name(productName);
	}
	
	public Page<Supplier> getBookByPaginationAndSort(int pageNumber,int pageSize,String field)
	{
		return supplierRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
}
