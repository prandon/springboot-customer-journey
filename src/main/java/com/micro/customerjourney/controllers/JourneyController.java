package com.micro.customerjourney.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.customerjourney.models.Customer;
import com.micro.customerjourney.models.Journey;
import com.micro.customerjourney.models.JourneyRequest;
import com.micro.customerjourney.repo.CustomerRepository;
import com.micro.customerjourney.repo.JourneyRepository;

@RestController
public class JourneyController {

	private JourneyRepository repository;
	private CustomerRepository customerRepository;
	
	public JourneyController(JourneyRepository repository, CustomerRepository customerRepository) {
		this.repository = repository;
		this.customerRepository = customerRepository;
	}
	
	@PostMapping("/journey")
	public Journey saveJourney(@RequestBody JourneyRequest request) {
		Journey journey = getFare(request);
		return repository.save(journey);
	}
	
	@GetMapping("/journey")
	public List<Journey> getJourney(){
		return repository.findAll();
	}
	
	@PostMapping("/journey/fare")
	public Journey getFare(@RequestBody JourneyRequest request) {
		Journey journey = new Journey(request);
		Optional<Customer> customerData = customerRepository.findById(request.getCustomerId());
		Customer customer = customerData.get();
		Double price;
		
		//if customer is VIP, price 1$ else 2$
		if (customer.getCustomerType().equals("VIP")) {
			price = 1.0;
		}
		else {
			price = 2.0;
		}
		
		//if requested journey route is customer's favorite route, discount of 30% applicable
		//but no discount for Persona Non Grata customers
		if (!customer.getCustomerType().equals("Persona Non Grata") && journey.getRoute().equals(customer.getFavoriteRoute())) {
			price = price - (price*0.3);
		}
		
		//additional 10% discount for booking same route in 48hrs
		for(Journey j : customer.getJourneys()) {
			if (j.getRoute().equals(journey.getRoute()) && isDiffrenceGreaterThan48(j.getBookingTimestamp())) {
				price = price - (price*0.1);
				break;
			}
		}
		
		journey.setPrice(price);
		
		return journey;
	}
	
	private boolean isDiffrenceGreaterThan48(Timestamp timestamp) {
		long current = new Date().getTime();
		long oldLong = timestamp.getTime();
		
		long diff = current - oldLong;
		long diffHours = diff / (60 * 60 * 1000);
		
		return diffHours < 48;
	}
	
}
