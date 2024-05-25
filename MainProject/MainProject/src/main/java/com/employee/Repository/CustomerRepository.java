package com.employee.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	

}
