package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {


}
