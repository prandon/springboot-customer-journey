package com.micro.customerjourney.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.micro.customerjourney.models.Customer;


@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
