package com.micro.customerjourney.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	private String customerType;
	private String customerName;
	private String favoriteRoute;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Journey> journeys;

	public Customer() {
	}
	
	public Customer(Long customerId) {
		this.customerId = customerId;
	}

	public Customer(String customerType, String customerName, String favoriteRoute) {
		this.customerType = customerType;
		this.customerName = customerName;
		this.favoriteRoute = favoriteRoute;
	}

	public Set<Journey> getJourneys() {
		return journeys;
	}

	public void setJourneys(Set<Journey> journeys) {
		this.journeys = journeys;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFavoriteRoute() {
		return favoriteRoute;
	}

	public void setFavoriteRoute(String favoriteRoute) {
		this.favoriteRoute = favoriteRoute;
	}

}
