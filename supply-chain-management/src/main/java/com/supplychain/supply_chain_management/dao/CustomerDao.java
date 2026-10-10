package com.supplychain.supply_chain_management.dao;

import com.supplychain.supply_chain_management.entity.Customer;
import com.supplychain.supply_chain_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDao {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Optional<Customer> getCustomerByPhoneNumber(long phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Customer> getCustomerByOrderId(int orderId) {
        return customerRepository.findCustomerByOrderId(orderId);
    }

    public Page<Customer> getCustomerByPaginationAndSort(int pageNumber, int pageSize, String field) {
        return customerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}