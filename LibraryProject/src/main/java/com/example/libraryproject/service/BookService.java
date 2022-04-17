package com.example.libraryproject.service;


import com.example.libraryproject.exceptions.NoAuthorFoundException;
import com.example.libraryproject.exceptions.NoBookDetailsFoundException;
import com.example.libraryproject.exceptions.NoBookFoundException;
import com.example.libraryproject.exceptions.NoPublisherFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.BookDetails;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository, AuthorRepository authorRepository, LibraryRepository libraryRepository, PublisherRepository publisherRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.libraryRepository = libraryRepository;
        this.publisherRepository = publisherRepository;
    }


    public Book saveBook(Book book, int bookDetailsId, int authorId, int publisherId) {
        BookDetails BookDetails = bookDetailsRepository.findById(bookDetailsId)
                .orElseThrow(() -> new NoBookDetailsFoundException("No bookDetails with this id was found"));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NoAuthorFoundException("No author with this id was found"));

        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new NoPublisherFoundException("No publisher with this id was found"));


        book.setBookDetails(BookDetails);
        book.setAuthor(author);
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    public Book retrieveBookByName(String bookName) {
        Book book = bookRepository.findBookByBookName(bookName);

        if(book != null) return book;
        else throw new NoBookFoundException("No book with this name was found");
    }


    public List<Book> retrieveBooks() {
        return bookRepository.findAll();
    }


    public List<Book> retrieveBooksByMaxNoOfPages(int noOfPages) {

        List<Book> books = retrieveBooks()
                .stream()
                .filter(p -> p.getBookDetails().getNoOfPages() <= noOfPages)
                .collect(Collectors.toList());

        if(books != null && !books.isEmpty())
            return books;
        else throw new NoBookFoundException("No books with less than this number of pages was found");
    }



    public Book updateBookPublisher(int bookId, int publisherId) {

        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(() -> new NoPublisherFoundException("No publisher with this id was found"));

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("No book with this id was found"));

        book.setPublisher(publisher);



        return bookRepository.save(book);

    }

    public Book updateBookName(int bookId, String bookName) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("No book with this id was found"));

        book.setBookName(bookName);

        return bookRepository.save(book);

    }

    public Book retrieveBookById(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("No book with this id was found"));
    }

    public List<Book> retrieveBooksByMaxPrice(double price) {

        List<Book> books = retrieveBooks()
                .stream()
                .filter(p -> p.getBookDetails().getPrice() <= price)
                .collect(Collectors.toList());


        return books;
    }

    public Book updateNoOfPages(int bookId, int noOfPages) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("No book with this id was found"));
        BookDetails bookDetails = book.getBookDetails();
        bookDetails.setNoOfPages(noOfPages);

        return bookRepository.save(book);
    }

    public Book updatePrice(int bookId, double price) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("No book with this id was found"));
        BookDetails bookDetails = book.getBookDetails();
        bookDetails.setPrice(price);

        return bookRepository.save(book);
    }
    public Book updateDescription(int bookId, String description) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoBookFoundException("No book with this id was found"));
        BookDetails bookDetails = book.getBookDetails();
        bookDetails.setDescription(description);

        return bookRepository.save(book);
    }





//    @Transactional
//    public void deleteBooksWithAuthorId(int authorId) {
//        Optional<Book> books = retrieveBooks()
//                .stream()
//                .filter(p -> p.getAuthor().getAuthorId() == authorId)
//                .findAny();
//        //.orElseThrow(() -> new NoAuthorFoundException("The given product id does not exist!!!!"));
//
//        if (books != null && !books.isEmpty()) {
//            List<Integer> bookIds = books.stream()
//                    .map(Book::getBookId)
//                    .collect(Collectors.toList());
//
//            deleteManyById(bookIds);
//        }
//    }
//
//    public void deleteManyById(List<Integer> ids) {
//        bookRepository.deleteByBookIdIn(ids);
//    }


}
