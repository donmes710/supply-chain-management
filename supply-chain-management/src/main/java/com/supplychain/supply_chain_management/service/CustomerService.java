package com.supplychain.supply_chain_management.service;

import com.supplychain.supply_chain_management.dao.CustomerDao;
import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Customer;
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
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Customer information saved successfully.");
        response.setData(customerDao.saveCustomer(customer));
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id) 
    {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        
        Optional<Customer> opt = customerDao.getCustomerById(id);

        if (opt.isPresent()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer with ID " + id + " fetched successfully.");
            response.setData(opt.get());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new IdNotFoundException("Customer with ID " + id + " is not available in the database.");
    }

    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() 
    {
        ResponseStructure<List<Customer>> response = new ResponseStructure<>();
        List<Customer> customers = customerDao.getAllCustomers();
        
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All customer information fetched.");
        response.setData(customers);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) 
    {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.getCustomerById(customer.getId());

        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer with ID " + customer.getId() + " has been updated.");
            response.setData(customerDao.saveCustomer(customer));
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("Cannot update. No customer record available with the entered ID.");
    }

    public ResponseEntity<ResponseStructure<String>> deleteCustomer(Integer id) 
    {
        ResponseStructure<String> response = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.getCustomerById(id);

        if (opt.isPresent()) {
            customerDao.deleteCustomer(opt.get());
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer Information Deleted");
            response.setData("Customer with ID " + id + " has been deleted.");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("Cannot delete. No record available with the entered ID.");
    }

    public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhoneNumber(long phoneNumber) 
    {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.getCustomerByPhoneNumber(phoneNumber);

        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer with phone number " + phoneNumber + " fetched successfully.");
            response.setData(opt.get());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("Customer with phone number " + phoneNumber + " not found.");
    }

    public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(int orderId)
    {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.getCustomerByOrderId(orderId);

        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer for order ID " + orderId + " fetched successfully.");
            response.setData(opt.get());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        throw new NoRecordAvailableException("Customer associated with order ID " + orderId + " not found.");
    }
    
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSort(int pageNumber, int pageSize, String field) 
    {
        ResponseStructure<Page<Customer>> response = new ResponseStructure<>();
        Page<Customer> customers = customerDao.getCustomerByPaginationAndSort(pageNumber, pageSize, field);
        
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Customer data fetched with pagination and sorting.");
        response.setData(customers);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}