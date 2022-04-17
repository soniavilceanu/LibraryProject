package com.example.libraryproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class BookDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookDetailsId;

    @Min(value = 80, message = "The number of pages cannot be lower than 80")
    private int noOfPages;

    @Size(min = 10, message = "The description must be longer than 10 characters")
    private String description;

    @Min(value = 20, message = "The price cannot be lower than 20")
    private double price;

    public BookDetails() {
    }

    public BookDetails(String description, int noOfPages, double price) {
        this.description = description;
        this.noOfPages = noOfPages;
        this.price = price;
    }

    public int getBookDetailsId() {
        return bookDetailsId;
    }

    public void setBookDetailsId(int bookDetailsId) {
        this.bookDetailsId = bookDetailsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
