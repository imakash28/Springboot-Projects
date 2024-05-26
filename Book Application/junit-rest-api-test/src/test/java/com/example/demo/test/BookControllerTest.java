package com.example.demo.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import com.example.demo.controller.BookController;
import com.example.demo.model.Book;
import com.example.demo.repo.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class BookControllerTest {

	private MockMvc mockMvc;
	
	ObjectMapper objectMapper=new ObjectMapper();
	ObjectWriter objectWriter=objectMapper.writer();
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookController bookController;
	
	// Lets create some test data:
	
	Book RECORD_1=new Book("1L","Atomic Habits","How to build Atomic Habits",5);
	Book RECORD_2=new Book("2L","Thinking Fast and Slow","How to create good mental models about thinking",6);
	Book RECORD_3=new Book("3L","Grokking Algorithms","Learn Algorithms in a fun way", 8);

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup(bookController).build();
	}
	@Test
	public void getAllRecords_success() throws Exception{
		List<Book> records=new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
		
		Mockito.when(bookRepository.findAll()).thenReturn(records);
		
		mockMvc.perform(MockMvcBuilders
					.get("/book")
			        .contentType(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
			        .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
			        .andExpect((ResultMatcher) jsonPath("$[2].name", is("Grokking Algorithms"))
			    );


	}
}