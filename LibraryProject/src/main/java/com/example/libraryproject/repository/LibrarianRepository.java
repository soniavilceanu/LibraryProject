package com.example.libraryproject.repository;

import com.example.libraryproject.model.Librarian;
import com.example.libraryproject.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Librarian findAllByLibrarianName(String name);

}
