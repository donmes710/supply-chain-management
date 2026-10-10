package com.supplychain.supply_chain_management.controller;

import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Product;
import com.supplychain.supply_chain_management.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. addProduct
    @PostMapping
    public ResponseEntity<ResponseStructure<Product>> saveProduct(@Valid @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // 2. getProductById
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // 3. getAllProduct
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts() {
        return productService.getAllProducts();
    }

    // 4. updateProduct
    @PutMapping
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    // 5. deleteProduct
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    // 6. getProductBySupplier
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<ResponseStructure<List<Product>>> getProductsBySupplierId(@PathVariable int supplierId) {
        return productService.getProductsBySupplierId(supplierId);
    }

    // 7. getProductByPaginationAndSorting
    @GetMapping("/page/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPaginationAndSort(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) 
    {
        return productService.getProductByPaginationAndSort(pageNumber, pageSize, field);
    }

    // 8. getProductStockQuantity
    @GetMapping("/{id}/stock")
    public ResponseEntity<ResponseStructure<Integer>> getProductStockQuantity(@PathVariable int id) {
        return productService.getProductStockQuantity(id);
    }
}