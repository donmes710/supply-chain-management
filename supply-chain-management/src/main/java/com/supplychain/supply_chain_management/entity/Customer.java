package com.supplychain.supply_chain_management.entity;

import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;



@Entity
public class Customer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Customer name cannot be blank")
	private String name;
	@NotBlank(message = "Email Cannot Be Blank")
	@Email(message = "Invalid Email Format")
	private String email;
	@Min(value = 1000000000L, message = "Phone number must be 10 digits")
	@Max(value = 9999999999L, message = "Phone number must be 10 digits")
	private long phoneNumber;
	@NotBlank(message = "Address cannot be blank")
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Set<Order> order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}
	
	

}
