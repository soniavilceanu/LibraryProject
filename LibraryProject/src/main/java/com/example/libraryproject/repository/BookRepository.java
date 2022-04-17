package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByBookName(String name);
}
