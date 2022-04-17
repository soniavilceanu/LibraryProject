package com.example.libraryproject.repository;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findAuthorByAuthorName(String name);

}
