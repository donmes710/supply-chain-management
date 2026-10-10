package com.supplychain.supply_chain_management.dao;

import com.supplychain.supply_chain_management.entity.Order;
import com.supplychain.supply_chain_management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Order order) 
    {
        orderRepository.delete(order);
    }

    public Optional<Order> getOrderByTrackingNumber(String trackingNumber) {
        return orderRepository.findByTrackingNumber(trackingNumber);
    }

    public List<Order> getOrdersByTotalAmountGreaterThan(int amount) {
        return orderRepository.findByTotalAmountGreaterThan(amount);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Page<Order> getOrderByPaginationAndSort(int pageNumber, int pageSize, String field) {
        return orderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}