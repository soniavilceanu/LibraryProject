package com.example.libraryproject.Service;

import com.example.libraryproject.exceptions.NoAuthorFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.repository.AuthorRepository;
import com.example.libraryproject.repository.BookDetailsRepository;
import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.repository.LibraryRepository;
import com.example.libraryproject.service.AuthorService;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {



    @InjectMocks
    private AuthorService authorService;

    @Mock
    private BookDetailsRepository bookDetailsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private LibraryRepository libraryRepository;

    @Test
    @DisplayName("Runnning save Author in a happy flow!")
    void saveNewAuthorHappyFlow(){
        //arrange - define actions for mocks

        Author author1 = new Author("Author Test1", "0799454223");

        when(authorRepository.save(author1)).thenReturn(author1);

        //act - call the injectmock method

        Author result = authorService.saveNewAuthor(author1);

        //assert - check the result based on arrange and act

        assertEquals(author1.getAuthorName(), result.getAuthorName());
    }

    @Test
    @DisplayName("Runnning retrieve Author by name in a happy flow!")
    void retrieveAuthorByNameHappyFlow(){
        String authorName = "Author Test";
        Author author = new Author();
        author.setAuthorName(authorName);

        when(authorRepository.findAuthorByAuthorName(authorName)).thenReturn(author);

        Author result  = authorService.retrieveAuthorByName(authorName);

        assertEquals(authorName, result.getAuthorName());
    }

    @Test
    @DisplayName("Runnning retrieve Author by name in a negative flow!")
    void retrieveAuthorByNameNegativeFlow(){
        String authorName = "Author Test";
        Author author = new Author();
        author.setAuthorName(authorName);

        when(authorRepository.findAuthorByAuthorName(authorName)).thenReturn(null);

       RuntimeException exception = assertThrows(NoAuthorFoundException.class,
               () -> authorService.retrieveAuthorByName(authorName));

        assertEquals("No author with this name was found", exception.getMessage());
    }


    @Test
    @DisplayName("Runnning retrieve Authors in a happy flow!")
    void retrieveAuthorsHappyFlow(){

        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Author Test 1", "0988737222");
        Author author2 = new Author("Author Test 2", "0234565443");
        authors.add(author1);
        authors.add(author2);

        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result  = authorService.retrieveAuthors();

        assertEquals(author1.getAuthorName(), result.get(0).getAuthorName());
        assertEquals(author2.getAuthorName(), result.get(1).getAuthorName());
        assertEquals(author1.getContact(), result.get(0).getContact());
        assertEquals(author2.getContact(), result.get(1).getContact());
    }


    @Test
    @DisplayName("Runnning update Author Contact in a happy flow!")
    void updateContactHappyFlow(){

        int authorId = 1;
        String contact = "0988737111";

        Author author = new Author();
        author.setAuthorId(authorId);


        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.updateContact(authorId,contact);

        assertEquals(contact, result.getContact());

    }

    @Test
    @DisplayName("Runnning update Author Contact by name in a negative flow!")
    void updateContactNegativeFlow(){

        int authorId = 1;
        String contact = "0988737111";
        //Author author = new Author("Author Test 1", "0988737222");


        Author author = new Author();
        author.setAuthorId(authorId);

        java.util.Optional<Author> opt = Optional.empty();

        when(authorRepository.findById(authorId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoAuthorFoundException.class,
                () -> authorService.updateContact(authorId,contact));

        assertEquals("No author with this id was found", exception.getMessage());
    }

    @Test
    @DisplayName("Runnning update Author Name in a happy flow!")
    void updateAuthorNameHappyFlow(){

        int authorId = 1;
        String authorName = "AuthorName Test";

        Author author = new Author();
        author.setAuthorId(authorId);


        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.updateAuthorName(authorId,authorName);

        assertEquals(authorName, result.getAuthorName());

    }

    @Test
    @DisplayName("Runnning update Author Name by name in a negative flow!")
    void updateAuthorNameNegativeFlow(){

        int authorId = 1;
        String authorName = "AuthorName Test";
        //Author author = new Author("Author Test 1", "0988737222");


        Author author = new Author();
        author.setAuthorId(authorId);

        java.util.Optional<Author> opt = Optional.empty();

        when(authorRepository.findById(authorId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoAuthorFoundException.class,
                () -> authorService.updateAuthorName(authorId,authorName));

        assertEquals("No author with this id was found", exception.getMessage());
    }



    @Test
    @DisplayName("Runnning retrieve Author by Id in a happy flow!")
    void retrieveAuthorByIdHappyFlow(){
        int authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        Author result  = authorService.retrieveAuthorById(authorId);

        assertEquals(author.getAuthorId(), result.getAuthorId());
    }

    @Test
    @DisplayName("Runnning retrieve Author by Id in a negative flow!")
    void retrieveAuthorByIdNegativeFlow(){
        int authorId = 1;
        Author author = new Author();
        author.setAuthorId(authorId);

        java.util.Optional<Author> opt = Optional.empty();


        when(authorRepository.findById(authorId)).thenReturn(opt);

        RuntimeException exception = assertThrows(NoAuthorFoundException.class,
                () -> authorService.retrieveAuthorById(authorId));

        assertEquals("No author with this id was found", exception.getMessage());
    }


//    @Test
//    @DisplayName("Runnning retrieve Books in a happy flow!")
//    void retrieveBooksHappyFlow(){
//
//
//        List<Book> books = new ArrayList<>();
//        Book book1 = new Book("Book1");
//        Book book2 = new Book("Book2");
//        books.add(book1);
//        books.add(book2);
//
//        int authorId = 1;
//
//        Author author = new Author("Author Test 1", "0988737222");
//
//        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
//
//        List<Book> result  = authorService.retrieveBooks(authorId);
//
//
//        assertNotNull(result);
//        assertEquals(book1.getBookId(), result.get(0).getBookId());
//        assertEquals(book2.getBookId(), result.get(1).getBookId());
//
//    }
//
//    @Test
//    @DisplayName("Runnning retrieve Books in a negative flow!")
//    void retrieveBooksNegativeFlow(){
//        int authorId = 1;
//        Author author = new Author();
//        author.setAuthorId(authorId);
//
//        java.util.Optional<Author> opt = Optional.empty();
//
//
//        when(authorRepository.findById(authorId)).thenReturn(opt);
//
//        RuntimeException exception = assertThrows(NoAuthorFoundException.class,
//                () -> authorService.retrieveAuthorById(authorId));
//
//        assertEquals("No author with this id was found", exception.getMessage());
//    }

}
