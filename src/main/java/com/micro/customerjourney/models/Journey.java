package com.micro.customerjourney.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "journey")
public class Journey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Customer customer;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date startDateTime;
	private String route;
	private Double price;
	private Timestamp bookingTimestamp;

	public Journey() {
	}

	public Journey(JourneyRequest request) {
		this.customer = new Customer(request.getCustomerId());
		this.startDateTime = request.getStartDateTime();
		this.route = request.getRoute();
		this.bookingTimestamp = new Timestamp(new Date().getTime());
	}

	public Journey(Customer customer, Date startDateTime, String route) {
		super();
		this.customer = customer;
		this.startDateTime = startDateTime;
		this.route = route;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		customer.setJourneys(null);
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Timestamp getBookingTimestamp() {
		return bookingTimestamp;
	}

	public void setBookingTimestamp(Timestamp bookingTimestamp) {
		this.bookingTimestamp = bookingTimestamp;
	}
	

}