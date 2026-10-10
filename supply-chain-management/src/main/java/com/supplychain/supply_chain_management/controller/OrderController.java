package com.supplychain.supply_chain_management.controller;

import com.supplychain.supply_chain_management.dto.ResponseStructure;
import com.supplychain.supply_chain_management.entity.Order;
import com.supplychain.supply_chain_management.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {

    @Autowired
    private OrderService orderService;

    // 1. placeOrder
    @PostMapping
    public ResponseEntity<ResponseStructure<Order>> placeOrder(@Valid @RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // 2. getOrderById
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Order>> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    // 3. getAllOrders
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Order>>> getAllOrders() {
        return orderService.getAllOrders();
    }

    // 4. updateOrder
    @PutMapping
    public ResponseEntity<ResponseStructure<Order>> updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    // 5. deleteOrder
    @DeleteMapping("{id}")
    public ResponseEntity<ResponseStructure<String>> deleteOrder(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }

    // 6. getOrderByTrackingNumber
    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<ResponseStructure<Order>> getOrderByTrackingNumber(@PathVariable String trackingNumber) {
        return orderService.getOrderByTrackingNumber(trackingNumber);
    }

    // 7. getOrderWithAmountGreaterThanParticularValue
    @GetMapping("/amount-greater-than/{amount}")
    public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByTotalAmountGreaterThan(@PathVariable int amount) {
        return orderService.getOrdersByTotalAmountGreaterThan(amount);
    }

    // 8. getOrderByStatus
    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByStatus(@PathVariable String status) {
        return orderService.getOrdersByStatus(status);
    }

    // 9. getOrderByCustomerId
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ResponseStructure<List<Order>>> getOrdersByCustomerId(@PathVariable int customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    // 10. getOrderByPaginationAndSorting
    @GetMapping("/page/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Order>>> getOrderByPaginationAndSort(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {
        return orderService.getOrderByPaginationAndSort(pageNumber, pageSize, field);
    }
}