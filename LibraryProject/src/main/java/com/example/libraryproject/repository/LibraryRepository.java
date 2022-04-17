package com.example.libraryproject.repository;

import com.example.libraryproject.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Library findAllByLocation(String location);
}
