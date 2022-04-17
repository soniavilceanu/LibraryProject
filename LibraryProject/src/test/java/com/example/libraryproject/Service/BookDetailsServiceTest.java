package com.example.libraryproject.Service;

import com.example.libraryproject.exceptions.NoAuthorFoundException;
import com.example.libraryproject.exceptions.NoBookDetailsFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.BookDetails;
import com.example.libraryproject.repository.AuthorRepository;
import com.example.libraryproject.repository.BookDetailsRepository;
import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.repository.LibraryRepository;
import com.example.libraryproject.service.AuthorService;
import com.example.libraryproject.service.BookDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookDetailsServiceTest {




    @InjectMocks
    private BookDetailsService bookDetailsService;

    @Mock
    private BookDetailsRepository bookDetailsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private LibraryRepository libraryRepository;


    @Test
    @DisplayName("Runnning save BookDetails in a happy flow!")
    void saveNewBookDetailsHappyFlow(){

        BookDetails bookDetails1 = new BookDetails("BookDetails Test1", 500,90);

        when(bookDetailsRepository.save(bookDetails1)).thenReturn(bookDetails1);

        BookDetails result = bookDetailsService.saveNewBookDetails(bookDetails1);

        assertEquals(bookDetails1.getPrice(), result.getPrice());
        assertEquals(bookDetails1.getDescription(), result.getDescription());
        assertEquals(bookDetails1.getNoOfPages(), result.getNoOfPages());
    }
    

    @Test
    @DisplayName("Runnning retrieve BookDetailss in a happy flow!")
    void retrieveBookDetailssHappyFlow(){

        List<BookDetails> bookDetailss = new ArrayList<>();
        BookDetails bookDetails1 = new BookDetails("BookDetails Test 1", 470,89.99);
        BookDetails bookDetails2 = new BookDetails("BookDetails Test 2", 200, 56);
        bookDetailss.add(bookDetails1);
        bookDetailss.add(bookDetails2);

        when(bookDetailsRepository.findAll()).thenReturn(bookDetailss);

        List<BookDetails> result  = bookDetailsService.retrieveBookDetails();

        assertEquals(bookDetails1.getDescription(), result.get(0).getDescription());
        assertEquals(bookDetails2.getDescription(), result.get(1).getDescription());

        assertEquals(bookDetails2.getPrice(), result.get(1).getPrice());
        assertEquals(bookDetails1.getPrice(), result.get(0).getPrice());

        assertEquals(bookDetails2.getNoOfPages(), result.get(1).getNoOfPages());
        assertEquals(bookDetails1.getNoOfPages(), result.get(0).getNoOfPages());


    }


    @Test
    @DisplayName("Runnning retrieve BookDetails by Id in a happy flow!")
    void retrieveBookDetailsByIdHappyFlow(){
        int bookDetailsId = 1;
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookDetailsId(bookDetailsId);

        when(bookDetailsRepository.findById(bookDetailsId)).thenReturn(Optional.of(bookDetails));

        BookDetails result  = bookDetailsService.retrieveBookDetailsById(bookDetailsId);

        assertEquals(bookDetails.getBookDetailsId(), result.getBookDetailsId());
    }

    @Test
    @DisplayName("Runnning retrieve BookDetails by Id in a negative flow!")
    void retrieveBookDetailsByIdNegativeFlow(){
        int bookDetailsId = 1;
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookDetailsId(bookDetailsId);

        java.util.Optional<BookDetails> opt = Optional.empty();


        when(bookDetailsRepository.findById(bookDetailsId)).thenReturn(opt);

        RuntimeException exception = assertThrows(NoBookDetailsFoundException.class,
                () -> bookDetailsService.retrieveBookDetailsById(bookDetailsId));

        assertEquals("No bookDetails with this id were found", exception.getMessage());
    }
}
