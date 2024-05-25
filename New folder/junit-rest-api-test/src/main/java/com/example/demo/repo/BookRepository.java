package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

	Optional<Book> findById(String bookId);

}
