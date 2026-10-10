package com.supplychain.supply_chain_management.repository;

import com.supplychain.supply_chain_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByTrackingNumber(String trackingNumber);

    List<Order> findByTotalAmountGreaterThan(int amount);

    List<Order> findByStatus(String status);

    List<Order> findByCustomerId(int customerId);
}