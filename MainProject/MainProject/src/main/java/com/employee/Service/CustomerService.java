package com.employee.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.employee.Exception.CustomerNotFoundException;
import com.employee.Repository.CustomerRepository;
import com.employee.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRespository;
	// private String ;

	@Autowired
	MongoTemplate template;

	public Customer create(Customer customer) {
		// TODO Auto-generated method stub
		customer.setCustomer_shipping_status("Delivered");
		return this.customerRespository.save(customer);
	}

	public Customer update(Customer customer, String _id) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Customer customer1 = this.customerRespository.findById(_id)
				.orElseThrow(() -> new CustomerNotFoundException("status not found"));
		customer1.setCustomer_name(customer.getCustomer_name());
		customer1.setCustomer_id(customer.getCustomer_id());
		customer1.setCustomer_address(customer.getCustomer_address());
		Customer save = this.customerRespository.save(customer1);

		return save;
	}

	public void delete(String _id) {
		// TODO Auto-generated method stub
		Customer customer1 = this.customerRespository.findById(_id)
				.orElseThrow(() -> new CustomerNotFoundException("status not found"));
		this.customerRespository.delete(customer1);
	}

	public Customer getBy(String _id) {
		// TODO Auto-generated method stub
		// customer.getCustomer_address("USA");
		Customer customer = this.customerRespository.findById(_id)
				.orElseThrow(() -> new CustomerNotFoundException("customer not found by the given id"));
		// Customer
		// customer2=this.customerRespository.findById(customer_address).orElseThrow(()->
		// new RuntimeException("status not found"));
		return customer;
	}

	// return customer2;
	public List<Customer> searchCustomerByCustomer_address(String customer_address) {

		Query query = new Query();
		Criteria criteria = null;
		criteria = Criteria.where("customer_address").is(customer_address);
		query.addCriteria(criteria);
		System.out.println(template.find(query, Customer.class, "customers"));
		return template.find(query, Customer.class, "customers");
	}

	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		List<Customer> all = this.customerRespository.findAll();
		return all;
	}

	public Customer getById(String _id) {
		Optional<Customer> optionalCustomer = customerRespository.findById(_id);
		try {
			if (optionalCustomer.isEmpty()) {
				throw new CustomerNotFoundException("Customer not found by the id: " + _id);
			}
			return optionalCustomer.get();
		} catch (CustomerNotFoundException ex) {
			// Handle the exception here
			// You can return a custom error message or do some logging
			throw ex;
		} catch (Exception ex) {
			// Handle any other exception here
			// You can return a generic error message or do some logging
			throw new RuntimeException("An error occurred while fetching the customer with id " + _id, ex);
		}
	}
}
