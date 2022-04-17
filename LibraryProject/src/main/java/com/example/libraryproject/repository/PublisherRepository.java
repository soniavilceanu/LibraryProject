package com.example.libraryproject.repository;

import com.example.libraryproject.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findPublisherByPublisherName(String publisherName);
}
