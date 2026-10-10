package com.supplychain.supply_chain_management.dao;

import com.supplychain.supply_chain_management.entity.Product;
import com.supplychain.supply_chain_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findByIdAndIsActiveIsTrue(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findByIsActiveTrue();
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public List<Product> getProductsBySupplierId(int supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }

    public Page<Product> getProductByPaginationAndSort(int pageNumber, int pageSize, String field) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}