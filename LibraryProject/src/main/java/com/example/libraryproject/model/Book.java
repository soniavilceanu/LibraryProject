package com.example.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotNull(message = "Book name cannot be null!!!")
    @NotEmpty(message = "Book name cannot be empty!!!")
    private String bookName;



    @OneToOne
    @JoinColumn(name = "book_details_id")
    private BookDetails bookDetails;

    @ManyToOne
    @JoinColumn(name = "author_id")
//    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
//    @JsonIgnore
    private Publisher publisher;

    @ManyToMany(mappedBy = "bookList")
    @JsonIgnore
    private List<Library> libraries = new ArrayList<>();

    public Book() {
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
