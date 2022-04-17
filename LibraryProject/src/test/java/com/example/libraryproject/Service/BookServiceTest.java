package com.example.libraryproject.Service;

import com.example.libraryproject.exceptions.NoAuthorFoundException;
import com.example.libraryproject.exceptions.NoBookFoundException;
import com.example.libraryproject.exceptions.NoPublisherFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.BookDetails;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.*;
import com.example.libraryproject.service.AuthorService;
import com.example.libraryproject.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDetailsRepository bookDetailsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private LibraryRepository libraryRepository;
    @Mock
    private PublisherRepository publisherRepository;





    @Test
    @DisplayName("Save Book using happy flow")
    void saveBookHappyFlow(){

        //arrange

        int bookDetailsId = 1;
        int authorId = 2;
        int publisherId = 1;


        Book book = new Book("Book1");
        BookDetails bookDetails = new BookDetails("Test Book details",500,45.5);
        Author author = new Author("Test author","0766454397");
        Publisher publisher = new Publisher("Test Publisher");

        when(bookDetailsRepository.findById(bookDetailsId)).thenReturn(Optional.of(bookDetails));
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(publisherRepository.findById(publisherId)).thenReturn(Optional.of(publisher));
        when(bookRepository.save(book)).thenReturn(book);

        //act
        Book result = bookService.saveBook(book,bookDetailsId,authorId,publisherId);

        //assert
        assertEquals(book.getBookName(),result.getBookName());

        assertEquals(author.getAuthorName(), result.getAuthor().getAuthorName());
        assertEquals(bookDetails.getDescription(), result.getBookDetails().getDescription());
        assertEquals(bookDetails.getNoOfPages(), result.getBookDetails().getNoOfPages());
        assertEquals(bookDetails.getPrice(), result.getBookDetails().getPrice());
        assertEquals(publisher.getPublisherName(), result.getPublisher().getPublisherName());
    }

    @Test
    @DisplayName("Runnning retrieve Book by name in a happy flow!")
    void retrieveBookByNameHappyFlow(){
        String bookName = "Book Test";
        Book book = new Book();
        book.setBookName(bookName);

        when(bookRepository.findBookByBookName(bookName)).thenReturn(book);

        Book result  = bookService.retrieveBookByName(bookName);

        assertEquals(bookName, result.getBookName());
    }

    @Test
    @DisplayName("Runnning retrieve Author by name in a negative flow!")
    void retrieveAuthorByNameNegativeFlow(){
        String bookName = "Book Test";
        Book book = new Book();
        book.setBookName(bookName);

        when(bookRepository.findBookByBookName(bookName)).thenReturn(null);


        RuntimeException exception = assertThrows(NoBookFoundException.class,
                () -> bookService.retrieveBookByName(bookName));

        assertEquals("No book with this name was found", exception.getMessage());
    }


    @Test
    @DisplayName("Runnning retrieve Books in a happy flow!")
    void retrieveBooksHappyFlow(){

        List<Book> books = new ArrayList<>();
        Book book1 = new Book("Book Test 1");
        Book book2 = new Book("Book Test 2");
        books.add(book1);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result  = bookService.retrieveBooks();

        assertEquals(book1.getBookName(), result.get(0).getBookName());
        assertEquals(book2.getBookName(), result.get(1).getBookName());

    }


    @Test
    @DisplayName("Runnning update Book Publisher in a happy flow!")
    void updatePublisherHappyFlow(){

        int bookId = 1;
        int publisherId = 1;


        Book book = new Book();
        book.setBookId(bookId);

        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(publisherRepository.findById(publisherId)).thenReturn(Optional.of(publisher));

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updateBookPublisher(bookId,publisherId);

        assertEquals(publisherId, result.getPublisher().getPublisherId());

    }

    @Test
    @DisplayName("Runnning update Book Publisher by name in a negative flow!")
    void updatePublisherNegativeFlow(){

        int bookId = 1;
        int publisherId = 1;


        Book book = new Book();
        book.setBookId(bookId);

        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherId);

        book.setPublisher(publisher);


        java.util.Optional<Publisher> opt1 = Optional.empty();
        java.util.Optional<Book> opt2 = Optional.empty();


        when(publisherRepository.findById(publisherId)).thenReturn(opt1);

        //when(bookRepository.findById(bookId)).thenReturn(opt2);




        RuntimeException exception1 = assertThrows(NoPublisherFoundException.class,
                () -> bookService.updateBookPublisher(bookId, publisherId));

       // RuntimeException exception2 = assertThrows(NoBookFoundException.class,
       //         () -> bookService.updateBookPublisher(bookId, publisherId));


        assertEquals("No publisher with this id was found", exception1.getMessage());
        //assertEquals("No book with this id was found", exception2.getMessage());
    }



    @Test
    @DisplayName("Runnning update Book Name in a happy flow!")
    void updateBookNameHappyFlow(){

        int bookId = 1;
        String bookName = "Book Name Test";

        Book book = new Book();
        book.setBookId(bookId);



        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updateBookName(bookId,bookName);

        assertEquals(bookName, result.getBookName());

    }


    @Test
    @DisplayName("Runnning update Author Contact by name in a negative flow!")
    void updateAuthorNameNegativeFlow(){

        int bookId = 1;
        String bookName = "Book Name Test";

        Book book = new Book();
        book.setBookId(bookId);



        java.util.Optional<Book> opt = Optional.empty();

        when(bookRepository.findById(bookId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoBookFoundException.class,
                () -> bookService.updateBookName(bookId,bookName));

        assertEquals("No book with this id was found", exception.getMessage());
    }



    @Test
    @DisplayName("Runnning retrieve Book by Id in a happy flow!")
    void retrieveBookByIdHappyFlow(){
        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result  = bookService.retrieveBookById(bookId);

        assertEquals(book.getBookId(), result.getBookId());
    }

    @Test
    @DisplayName("Runnning retrieve Book by Id in a negative flow!")
    void retrieveBookByIdNegativeFlow(){
        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);

        java.util.Optional<Book> opt = Optional.empty();


        when(bookRepository.findById(bookId)).thenReturn(opt);

        RuntimeException exception = assertThrows(NoBookFoundException.class,
                () -> bookService.retrieveBookById(bookId));

        assertEquals("No book with this id was found", exception.getMessage());
    }



    @Test
    @DisplayName("Updating number of pages in a happy flow!")
    void updateNoOfPagesHappyFlow(){

        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);
        int noOfPages = 600;

        BookDetails bookDetails = new BookDetails("Description Test",noOfPages,89.99);
        book.setBookDetails(bookDetails);



        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updateNoOfPages(bookId,noOfPages);

        assertEquals(book.getBookDetails().getNoOfPages(), result.getBookDetails().getNoOfPages());

    }


    @Test
    @DisplayName("Updating number of pages in a happy flow!")
    void updateNoOfPagesNegativeFlow(){

        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);
        int noOfPages = 600;

        BookDetails bookDetails = new BookDetails("Description Test",noOfPages,89.99);
        book.setBookDetails(bookDetails);



        java.util.Optional<Book> opt = Optional.empty();

        when(bookRepository.findById(bookId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoBookFoundException.class,
                () -> bookService.updateNoOfPages(bookId,noOfPages));

        assertEquals("No book with this id was found", exception.getMessage());
    }



    @Test
    @DisplayName("Updating book price in a happy flow!")
    void updatePriceHappyFlow(){

        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);
        double price = 89.99;

        BookDetails bookDetails = new BookDetails("Description Test",600,price);
        book.setBookDetails(bookDetails);



        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updatePrice(bookId,price);

        assertEquals(book.getBookDetails().getPrice(), result.getBookDetails().getPrice());

    }


    @Test
    @DisplayName("Updating book price in a negative flow!")
    void updatePriceNegativeFlow(){

        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);
        double price = 78.45;

        BookDetails bookDetails = new BookDetails("Description Test",799,price);
        book.setBookDetails(bookDetails);



        java.util.Optional<Book> opt = Optional.empty();

        when(bookRepository.findById(bookId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoBookFoundException.class,
                () -> bookService.updatePrice(bookId,price));

        assertEquals("No book with this id was found", exception.getMessage());
    }


    @Test
    @DisplayName("Updating book price in a happy flow!")
    void updateDescriptionHappyFlow(){

        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);
        double price = 89.99;
        String description = "Book Test Description";

        BookDetails bookDetails = new BookDetails(description,600,price);
        book.setBookDetails(bookDetails);



        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updateDescription(bookId,description);

        assertEquals(book.getBookDetails().getDescription(), result.getBookDetails().getDescription());

    }


    @Test
    @DisplayName("Updating book price in a negative flow!")
    void updateDescriptionNegativeFlow(){

        int bookId = 1;

        Book book = new Book();
        book.setBookId(bookId);
        double price = 78.45;

        String description = "Book Test Description";

        BookDetails bookDetails = new BookDetails(description,799,price);
        book.setBookDetails(bookDetails);



        java.util.Optional<Book> opt = Optional.empty();

        when(bookRepository.findById(bookId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoBookFoundException.class,
                () -> bookService.updateDescription(bookId,description));

        assertEquals("No book with this id was found", exception.getMessage());
    }


//    @Test
//    @DisplayName("Retrieve Books by max price in a happy flow!")
//    void retrieveBooksByMaxPriceHappyFlow(){
//
//        double maxPrice = 80;
//
//        List<Book> books = new ArrayList<>();
//        Book book1 = new Book("Book Test 1");
//        Book book2 = new Book("Book Test 2");
//
//
//        String description1 = "Book Test Description 1";
//        BookDetails bookDetails1 = new BookDetails(description1,799,60);
//        book1.setBookDetails(bookDetails1);
//
//
//        String description2 = "Book Test Description 2";
//        BookDetails bookDetails2 = new BookDetails(description2,1900,120);
//        book2.setBookDetails(bookDetails2);
//
//
//        books.add(book1);
//        books.add(book2);
//
//        when(bookRepository.findAll()).thenReturn(books);
//
//        List<Book> result  = bookService.retrieveBooksByMaxPrice(maxPrice);
//
//
//
//        assertEquals(book1.getBookDetails().getPrice(), result.get(0).getBookDetails().getPrice());
//        assertEquals(book2.getBookDetails().getPrice(), result.get(1).getBookDetails().getPrice());
//
//    }


}
