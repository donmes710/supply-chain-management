package com.supplychain.supply_chain_management.repository;

import com.supplychain.supply_chain_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByPhoneNumber(long phoneNumber);

    @Query("SELECT o.customer FROM Order o WHERE o.id = :orderId")
    Optional<Customer> findCustomerByOrderId(@Param("orderId") int orderId);
}