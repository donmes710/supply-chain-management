package com.supplychain.supply_chain_management.service;

import com.supplychain.supply_chain_management.dao.CustomerDao;
import com.supplychain.supply_chain_management.dao.OrderDao;
import com.supplychain.supply_chain_management.dao.ProductDao;
import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Customer;
import com.supplychain.supply_chain_management.entity.Order;
import com.supplychain.supply_chain_management.entity.Product;
import com.supplychain.supply_chain_management.entity.Supplier;
import com.supplychain.supply_chain_management.exception.IdNotFoundException;
import com.supplychain.supply_chain_management.exception.NoRecordAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

    public ResponseEntity<ResponseStructure<Order>> saveOrder(Order order) 
    {
        ResponseStructure<Order> response = new ResponseStructure<>();
        
        // validating customer
        int customerId = order.getCustomer().getId();
        Optional<Customer> optCustomer = customerDao.getCustomerById(customerId);
        
        if (optCustomer.isPresent()) 
        {
            order.setCustomer(optCustomer.get());
        } else 
        {
            throw new NoRecordAvailableException("Cannot Place The Order Since The Customer Is Not Found With The ID "+customerId);
        }
        
        
        // validating product 
        List<Product> productsFromRequest = order.getProduct(); 
        if (productsFromRequest == null || productsFromRequest.isEmpty()) 
        {
            throw new NoRecordAvailableException("Cannot Place Order. At least One Product Is Required.");
        }

        List<Product> fullProducts = new ArrayList<>(); 
        
        double totalOrderAmount=0;
        for (Product productFromRequest : productsFromRequest) 
        {
            int productId = productFromRequest.getId();
            Optional<Product> optProduct = productDao.getProductById(productId);
            
            if (optProduct.isEmpty()) 
            {
                throw new NoRecordAvailableException("Cannot place order. No product found with ID: " + productId);
            }
            
            Product productInDb = optProduct.get();
            
            // checking the stock quantity 
            if (productInDb.getStockQuantity() <= 0) 
            {
                throw new NoRecordAvailableException("Product " + productInDb.getName() + " is out of stock.");
            }
            // Deduct stock
            productInDb.setStockQuantity(productInDb.getStockQuantity() - 1); 
            productDao.saveProduct(productInDb); // Save the updated stock quantity
            
            totalOrderAmount += productInDb.getPrice();
            
            fullProducts.add(productInDb);
            
        }
        order.setProduct(fullProducts);
        order.setTotalAmount(totalOrderAmount);

     
        
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Order placed successfully.");
        response.setData(orderDao.saveOrder(order));
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Order>> getOrderById(int id) 
    {
        ResponseStructure<Order> response = new ResponseStructure<>();
        Optional<Order> opt = orderDao.getOrderById(id);

        if (opt.isPresent()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Order with ID " + id + " fetched successfully.");
            response.setData(opt.get());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new IdNotFoundException("Order with ID " + id + " not found.");
    }

    public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders() 
    {
        ResponseStructure<List<Order>> response = new ResponseStructure<>();
        
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All order information fetched.");
        response.setData(orderDao.getAllOrders());
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Order>> updateOrder(Order order) 
    {
        ResponseStructure<Order> response = new ResponseStructure<>();
        if (orderDao.getOrderById(order.getId()).isPresent()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Order with ID " + order.getId() + " has been updated.");
            response.setData(orderDao.saveOrder(order));
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("Cannot update. No order found with the given ID.");
    }

    public ResponseEntity<ResponseStructure<String>> deleteOrder(Integer id) 
    {
    	ResponseStructure<String> response = new ResponseStructure<>();
		
		Optional<Order> opt = orderDao.getOrderById(id);
		
		if(opt.isPresent())
		{
			
			orderDao.deleteOrder(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("The Order Information Deleted");
			response.setData("Order With Id "+id+" Deleted");
			
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}
		else
		{
			throw new IdNotFoundException("No Record Available With The Entered ID");
		}
    }

    public ResponseEntity<ResponseStructure<Order>> getOrderByTrackingNumber(String trackingNumber) 
    {
        ResponseStructure<Order> response = new ResponseStructure<>();
        Optional<Order> opt = orderDao.getOrderByTrackingNumber(trackingNumber);
        if (opt.isPresent()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Order fetched successfully.");
            response.setData(opt.get());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("Order with tracking number " + trackingNumber + " not found.");
    }
    
    public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByTotalAmountGreaterThan(int amount) 
    {
        ResponseStructure<List<Order>> response = new ResponseStructure<>();
        List<Order> orders = orderDao.getOrdersByTotalAmountGreaterThan(amount);
        if (!orders.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Orders with amount greater than " + amount + " fetched.");
            response.setData(orders);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("No orders found with amount greater than " + amount + ".");
    }

    public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByStatus(String status) 
    {
        ResponseStructure<List<Order>> response = new ResponseStructure<>();
        List<Order> orders = orderDao.getOrdersByStatus(status);
        if (!orders.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Orders with status '" + status + "' fetched.");
            response.setData(orders);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("No orders found with status '" + status + "'.");
    }

    public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByCustomerId(int customerId) 
    {
        ResponseStructure<List<Order>> response = new ResponseStructure<>();
        List<Order> orders = orderDao.getOrdersByCustomerId(customerId);
        if (!orders.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Orders for customer ID " + customerId + " fetched.");
            response.setData(orders);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new NoRecordAvailableException("No orders found for customer ID " + customerId + ".");
    }

    public ResponseEntity<ResponseStructure<Page<Order>>> getOrderByPaginationAndSort(int pageNumber, int pageSize, String field) 
    {
        ResponseStructure<Page<Order>> response = new ResponseStructure<>();
        Page<Order> orders = orderDao.getOrderByPaginationAndSort(pageNumber, pageSize, field);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Order data fetched with pagination and sorting.");
        response.setData(orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}