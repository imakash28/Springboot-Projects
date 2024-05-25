package com.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employee.Repository.CustomerRepository;
import com.employee.model.Customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MainProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	 private CustomerRepository customerRespository;



	   @Test
	     public void testCreateCustomer() {
	    Customer mockCustomer = new Customer();
	    mockCustomer.setCustomer_name("test");
	    mockCustomer.setCustomer_id(13);
	    mockCustomer.setCustomer_shipping_status("on the way for delivery");
	    mockCustomer.setCustomer_address("hong kong");
	 customerRespository.save(mockCustomer);
	 assertNotNull(customerRespository.findById(mockCustomer.get_id()).orElse(null));
	 }

      @Test
      public void testRetrieveNonexistentCustomer() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/customers/999")
	    .contentType(MediaType.APPLICATION_JSON))
	   .andExpect(status().isNotFound());
	    }

       @Test
       public void testUpdateNonexistentCustomer() throws Exception {
	       String requestBody = "{\"customer_name\":\"Jane Doe\",\"customer_address\":\"456 Elm St\",\"customer_shipping_status\":\"complete\"}";
           mockMvc.perform(MockMvcRequestBuilders.put("/customers/999")
	       .contentType(MediaType.APPLICATION_JSON)
	       .content(requestBody))
	       .andExpect(status().isNotFound());
           }

         @Test
	 public void testCreateNewCustomer() throws Exception{
        	 String requestBody = "{\"customer_name\":\"John Doe\",\"customer_address\":\"123 Main St\",\"customer_shipping_status\":\"inprogress\"}";
	 mockMvc.perform(MockMvcRequestBuilders.post("/customers")
	 .contentType(MediaType.APPLICATION_JSON)
	 .content(requestBody))
	 .andExpect(status().isCreated());}
}
