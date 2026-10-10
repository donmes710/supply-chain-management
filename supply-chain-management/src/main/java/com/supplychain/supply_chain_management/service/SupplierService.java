package com.supplychain.supply_chain_management.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.supplychain.supply_chain_management.dao.SupplierDao;
import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Supplier;
import com.supplychain.supply_chain_management.exception.IdNotFoundException;
import com.supplychain.supply_chain_management.exception.NoRecordAvailableException;

@Service
public class SupplierService 
{
	@Autowired
	private SupplierDao supplierDao;
	
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier)
	{
		ResponseStructure<Supplier> response = new ResponseStructure<Supplier>();
		
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("The Supplier Information Is Saved");
		response.setData(supplierDao.saveSupplier(supplier));
		
		return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.CREATED);
		
		
	}
	
		public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSupplier()
		{
			ResponseStructure<List<Supplier>> response = new ResponseStructure<>();
			
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All The Supplier Informations Fetched");
			response.setData(supplierDao.getAllSupplier());
			
			return new ResponseEntity<ResponseStructure<List<Supplier>>>(response,HttpStatus.OK);
			
			
		}
		
		
		public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(Integer id)
		{
			ResponseStructure<Supplier> response = new ResponseStructure<Supplier>();
			Optional<Supplier> opt = supplierDao.getSupplierById(id);
			
			if(opt.isPresent())
			{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("The Supplier with Id "+id+"Is Fetched");
			response.setData(opt.get());
			
			return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.OK);
			}
			else
			{
				throw new IdNotFoundException("Supplier With Id "+id+"Is Not Available In DataBase");
			}
			
		}
		
		public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier)
		{
			ResponseStructure<Supplier> response = new ResponseStructure<Supplier>();
			
			Optional<Supplier> opt = supplierDao.getSupplierById(supplier.getId());
			
			if(opt.isPresent())
			{
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("The Supplier with Id "+supplier.getId()+" Is Updated");
				response.setData(supplierDao.saveSupplier(supplier));
				
				return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.OK);
			}
			else
			{
				throw new IdNotFoundException("No Record Available With The Entered ID");
			}
		}
		
		public ResponseEntity<ResponseStructure<String>> deleteSupplier(Integer id)
		{
			ResponseStructure<String> response = new ResponseStructure();
			
			Optional<Supplier> opt = supplierDao.getSupplierById(id);
			
			if(opt.isPresent())
			{
				
				supplierDao.deleteSupplier(opt.get());
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("The Supplier Information Deleted");
				response.setData("Supplier With Id"+id+" Deleted");
				
				return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
			}
			else
			{
				throw new IdNotFoundException("No Record Available With The Entered ID");
			}
		}
		
		public ResponseEntity<ResponseStructure<List<Supplier>>> getSupplierByProductName(String productName)
		{
			ResponseStructure<List<Supplier>> response = new ResponseStructure<List<Supplier>>();
			
			List<Supplier> suppliers = supplierDao.getSupplierByProductName(productName);
			
			if(!suppliers.isEmpty())
			{
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("The Supplier Fetched Successfully");
				response.setData(suppliers);
				
				return new ResponseEntity<ResponseStructure<List<Supplier>>>(response,HttpStatus.OK);
			}
			else
			{
				throw new NoRecordAvailableException("The Supplier Not Found");
			}
		}
		
		public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting(int pageNumber,int pagesize,String field)
		{
			ResponseStructure<Page<Supplier>> response = new ResponseStructure<Page<Supplier>>();
			
			Page<Supplier> pages = supplierDao.getBookByPaginationAndSort(pageNumber, pagesize, field);
			
			if(!pages.isEmpty())
			{
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("The Supplier Page Successfully");
				response.setData(pages);
				
				return new ResponseEntity<ResponseStructure<Page<Supplier>>>(response,HttpStatus.OK);
			}
			else
			{
				throw new NoRecordAvailableException("The Supplier Page Not Found");
			}
		}
		
		
		
		
		
	

}
