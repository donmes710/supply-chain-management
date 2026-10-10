package com.supplychain.supply_chain_management.service;

import com.supplychain.supply_chain_management.dao.ProductDao;
import com.supplychain.supply_chain_management.dao.SupplierDao;
import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Product;
import com.supplychain.supply_chain_management.entity.Supplier;
import com.supplychain.supply_chain_management.exception.IdNotFoundException;
import com.supplychain.supply_chain_management.exception.NoRecordAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private SupplierDao supplierDao;

    public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) 
    {
        ResponseStructure<Product> response = new ResponseStructure<>();
        
        
        int supplierId = product.getSupplier().getId();
        Optional<Supplier> optSupplier = supplierDao.getSupplierById(supplierId);
        
        if (optSupplier.isPresent()) 
        {
            product.setSupplier(optSupplier.get());
        } else 
        {
            throw new NoRecordAvailableException("Cannot create product. No supplier found with ID: " + supplierId);
        }
   
        
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Product information saved successfully.");
        response.setData(productDao.saveProduct(product));
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Product>> getProductById(int id) 
    {
        ResponseStructure<Product> response = new ResponseStructure<>();
        Optional<Product> opt = productDao.getProductById(id);

        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Product with ID " + id + " fetched successfully.");
            response.setData(opt.get());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new IdNotFoundException("Product with ID " + id + " not found.");
    }

    public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts() 
    {
        ResponseStructure<List<Product>> response = new ResponseStructure<>();
        List<Product> products = productDao.getAllProducts();
        
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All product information fetched.");
        response.setData(products);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) 
    {
        ResponseStructure<Product> response = new ResponseStructure<>();
        Optional<Product> opt = productDao.getProductById(product.getId());

        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Product with ID " + product.getId() + " has been updated.");
            response.setData(productDao.saveProduct(product));
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new IdNotFoundException("Cannot update. No product record found with the given ID.");
    }

    public ResponseEntity<ResponseStructure<String>> deleteProduct(Integer id) 
    {
        ResponseStructure<String> response = new ResponseStructure<>();
        Optional<Product> opt = productDao.getProductById(id);

        if (opt.isPresent()) {
        	
            Product product=opt.get();
            product.setActive(false);
            productDao.saveProduct(product);
            
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Product Information Deleted");
            response.setData("Product with ID " + id + " has been deleted.");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new IdNotFoundException("Cannot delete. No record found with the given ID.");
    }

    public ResponseEntity<ResponseStructure<List<Product>>> getProductsBySupplierId(int supplierId) 
    {
        ResponseStructure<List<Product>> response = new ResponseStructure<>();
        List<Product> products = productDao.getProductsBySupplierId(supplierId);
        
        if (!products.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Products for supplier ID " + supplierId + " fetched successfully.");
            response.setData(products);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("No products found for supplier ID " + supplierId + ".");
    }

    public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPaginationAndSort(int pageNumber, int pageSize, String field) 
    {
        ResponseStructure<Page<Product>> response = new ResponseStructure<>();
        Page<Product> products = productDao.getProductByPaginationAndSort(pageNumber, pageSize, field);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Product data fetched with pagination and sorting.");
        response.setData(products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    public ResponseEntity<ResponseStructure<Integer>> getProductStockQuantity(int id) {
        ResponseStructure<Integer> response = new ResponseStructure<>();
        Optional<Product> optionalProduct = productDao.getProductById(id);

        if (optionalProduct.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Stock quantity for product ID " + id + " fetched.");
            response.setData(optionalProduct.get().getStockQuantity());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("Product with ID " + id + " not found.");
    }
}