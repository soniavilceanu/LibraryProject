package com.example.libraryproject.Service;

import com.example.libraryproject.exceptions.NoLibrarianFoundException;
import com.example.libraryproject.exceptions.NoPublisherFoundException;
import com.example.libraryproject.model.Librarian;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.*;
import com.example.libraryproject.service.LibrarianService;
import com.example.libraryproject.service.PublisherService;
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
public class LibrarianServiceTest {


    @InjectMocks
    private LibrarianService librarianService;

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
    @Mock
    private LibrarianRepository librarianRepository;


//    @Test
//    @DisplayName("Runnning save librarian in a happy flow!")
//    void saveNewLibrarianHappyFlow(){
//
//        int libraryId = 1;
//
//        Librarian librarian = new Librarian("Librarian Test", "email1@gmail.com");
//
//        when(librarianRepository.save(librarian)).thenReturn(librarian);
//
//
//        Librarian result = librarianService.saveLibrarian(librarian, libraryId);
//
//        assertEquals(librarian.getLibrarianName(), result.getLibrarianName());
//    }


    @Test
    @DisplayName("Runnning retrieve Librarian by name in a happy flow!")
    void retrieveLibrarianByNameHappyFlow(){
        String librarianName = "Librarian Test";
        Librarian librarian = new Librarian();
        librarian.setLibrarianName(librarianName);

        when(librarianRepository.findAllByLibrarianName(librarianName)).thenReturn(librarian);

        Librarian result  = librarianService.retrieveLibrarianByName(librarianName);

        assertEquals(librarianName, result.getLibrarianName());
    }

    @Test
    @DisplayName("Runnning retrieve Librarian by name in a negative flow!")
    void retrieveLibrarianByNameNegativeFlow(){
        String librarianName = "Librarian Test";
        Librarian librarian = new Librarian();
        librarian.setLibrarianName(librarianName);

        when(librarianRepository.findAllByLibrarianName(librarianName)).thenReturn(null);

        RuntimeException exception = assertThrows(NoLibrarianFoundException.class,
                () -> librarianService.retrieveLibrarianByName(librarianName));

        assertEquals("No librarian with this name was found", exception.getMessage());
    }


    @Test
    @DisplayName("Runnning retrieve Librarians in a happy flow!")
    void retrieveLibrariansHappyFlow(){

        List<Librarian> librarians = new ArrayList<>();
        Librarian librarian1 = new Librarian("Librarian Test 1", "email1@gmail.com");
        Librarian librarian2 = new Librarian("Librarian Test 2", "email2@gmail.com");
        librarians.add(librarian1);
        librarians.add(librarian2);

        when(librarianRepository.findAll()).thenReturn(librarians);

        List<Librarian> result  = librarianService.retrieveLibrarians();

        assertEquals(librarian1.getLibrarianName(), result.get(0).getLibrarianName());
        assertEquals(librarian2.getLibrarianName(), result.get(1).getLibrarianName());
        assertEquals(librarian1.getLibrarianName(), result.get(0).getLibrarianName());
        assertEquals(librarian2.getLibrarianName(), result.get(1).getLibrarianName());
    }



    @Test
    @DisplayName("Runnning update Librarian Contact in a happy flow!")
    void updateEmailHappyFlow(){

        int librarianId = 1;
        String email = "email1@gmail.com";

        Librarian librarian = new Librarian();
        librarian.setLibrarianId(librarianId);


        when(librarianRepository.findById(librarianId)).thenReturn(Optional.of(librarian));

        when(librarianRepository.save(librarian)).thenReturn(librarian);

        Librarian result = librarianService.updateEmail(librarianId,email);

        assertEquals(email, result.getEmail());

    }

    @Test
    @DisplayName("Runnning update Librarian Contact by name in a negative flow!")
    void updateEmailNegativeFlow(){

        int librarianId = 1;
        String email = "email1@gmail.com";


        Librarian librarian = new Librarian();
        librarian.setLibrarianId(librarianId);

        java.util.Optional<Librarian> opt = Optional.empty();

        when(librarianRepository.findById(librarianId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoLibrarianFoundException.class,
                () -> librarianService.updateEmail(librarianId,email));

        assertEquals("No librarian with this id was found", exception.getMessage());
    }

    @Test
    @DisplayName("Runnning retrieve Librarian by Id in a happy flow!")
    void retrieveLibrarianByIdHappyFlow(){
        int librarianId = 1;
        Librarian librarian = new Librarian();
        librarian.setLibrarianId(librarianId);

        when(librarianRepository.findById(librarianId)).thenReturn(Optional.of(librarian));

        Librarian result  = librarianService.retrieveLibrarianById(librarianId);

        assertEquals(librarian.getLibrarianId(), result.getLibrarianId());
    }

    @Test
    @DisplayName("Runnning retrieve Librarian by Id in a negative flow!")
    void retrieveLibrarianByIdNegativeFlow(){
        int librarianId = 1;
        Librarian librarian = new Librarian();
        librarian.setLibrarianId(librarianId);

        java.util.Optional<Librarian> opt = Optional.empty();


        when(librarianRepository.findById(librarianId)).thenReturn(opt);

        RuntimeException exception = assertThrows(NoLibrarianFoundException.class,
                () -> librarianService.retrieveLibrarianById(librarianId));

        assertEquals("No librarian with this id was found", exception.getMessage());
    }




}
