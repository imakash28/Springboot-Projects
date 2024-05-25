package com.employee.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Service.CustomerService;
import com.employee.model.Customer;

@RestController
@RequestMapping("/customers")
//@Api(value="Customer API",tags= {"customer"})

public class CustomerController {
	@Autowired
	private CustomerService customerService;

//     @GetMapping("/akhi")
//     public String akhila() {
//         return "akhila port is not available";
//     }
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer customer1 = this.customerService.create(customer);
		return new ResponseEntity<>(customer1, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/update-customer/{_id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable String _id) {
		Customer customer1 = this.customerService.update(customer, _id);
		return new ResponseEntity<>(customer1, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/delete-the-customer/{_id}")
	public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable String _id) {
		this.customerService.delete(_id);
		Map<String, String> message = Map.of("message", "You have successfully deleted");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/get-the-data/{_id}")
	// @ApiOperation(value="Get customer by id",notes="returns single customer based
	// on the id provied")

	public ResponseEntity<Customer> getCustomer(@PathVariable String _id) {
		Customer customer = this.customerService.getBy(_id);

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@GetMapping("/search-by-customer-address")
	public List<Customer> searchCustomerByCustomer_address(@RequestParam String customer_address) {

		return customerService.searchCustomerByCustomer_address(customer_address);
	}

	@GetMapping("")
	public ResponseEntity<List<Customer>> getAll() {
		List<Customer> all = this.customerService.getAll();
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

}
