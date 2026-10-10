package com.supplychain.supply_chain_management.controller;

import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Customer;
import com.supplychain.supply_chain_management.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. addCustomer
    @PostMapping
    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // 2. getCustomerById
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    // 3. getAllCustomer
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // 4. updateCustomer
    @PutMapping
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    // 5. deleteCustomer
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable Integer id) {
        return customerService.deleteCustomer(id);
    }

    // 6. getCustomerByPhoneNumber
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhoneNumber(@PathVariable long phoneNumber) {
        return customerService.getCustomerByPhoneNumber(phoneNumber);
    }

    // 7. getCustomerByOrder
    @GetMapping("/order/{orderId}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(@PathVariable int orderId) {
        return customerService.getCustomerByOrderId(orderId);
    }

    // 8. getCustomerByPaginationAndSorting
    @GetMapping("/page/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSort(@PathVariable int pageNumber,
           @PathVariable int pageSize,
           @PathVariable String field) 
    {
        return customerService.getCustomerByPaginationAndSort(pageNumber, pageSize, field);
    }
}