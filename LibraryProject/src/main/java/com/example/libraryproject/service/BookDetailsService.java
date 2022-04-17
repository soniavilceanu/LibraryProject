package com.example.libraryproject.service;

import com.example.libraryproject.exceptions.NoBookDetailsFoundException;
import com.example.libraryproject.exceptions.NoBookFoundException;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.BookDetails;
import com.example.libraryproject.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookDetailsService {


    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;
    private final PublisherRepository publisherRepository;

    private final BookService bookService;

    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository, AuthorRepository authorRepository,
                              LibraryRepository libraryRepository, PublisherRepository publisherRepository, BookService bookService) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.libraryRepository = libraryRepository;
        this.publisherRepository = publisherRepository;
        this.bookService = bookService;
    }

    public BookDetails saveNewBookDetails(BookDetails bookDetails) {
        return bookDetailsRepository.save(bookDetails);
    }


    public BookDetails retrieveBookDetailsById(int bookDetailsId) {
        return bookDetailsRepository.findById(bookDetailsId).orElseThrow(() -> new NoBookDetailsFoundException("No bookDetails with this id were found"));
    }


//    public void deleteById(int bookDetailsId){
//
//        //bookService.deleteBookWithBookDetailsId(bookDetailsId);
//        bookDetailsRepository.deleteById(bookDetailsId);
//
//    }

    public List<BookDetails> retrieveBookDetails() {
        return bookDetailsRepository.findAll();
    }

//    public List<BookDetails> retrieveBookDetailsByMaxNoOfPages(int noOfPages) {
//
//        List<BookDetails> bookDetails = retrieveBookDetails()
//                .stream()
//                .filter(p -> p.getNoOfPages() <= noOfPages)
//                .collect(Collectors.toList());
//


//                .stream()
//                .filter(p -> p.getNoOfPages() <= noOfPages)
//                .findAny()
//                .map(BookDetails::getBookDetailsId)
//                .collect(Collectors.toList());

        //.orElseThrow(() -> new NoAuthorFoundException("The given product id does not exist!!!!"));

//        if (bookDetails != null && !bookDetails.isEmpty()) {
//            List<Object> bookIds = bookDetails.stream()
//                    .map(BookDetails::getBookDetailsId)
//                    .collect(Collectors.toList());
//        }
//

//        return bookDetails;
//
//    }

}