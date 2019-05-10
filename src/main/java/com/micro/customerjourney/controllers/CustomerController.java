package com.micro.customerjourney.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.customerjourney.models.Customer;
import com.micro.customerjourney.repo.CustomerRepository;

@RestController
public class CustomerController {
	
	private CustomerRepository repository;
	
	public CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/customer")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return repository.save(customer);
	}
	
	@GetMapping("/customer")
	public List<Customer> getCustomers(){
		return repository.findAll();
	}
	
}
