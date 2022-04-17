package com.example.libraryproject.Service;

import com.example.libraryproject.exceptions.NoAuthorFoundException;
import com.example.libraryproject.exceptions.NoLibraryFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Librarian;
import com.example.libraryproject.model.Library;
import com.example.libraryproject.repository.AuthorRepository;
import com.example.libraryproject.repository.BookDetailsRepository;
import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.repository.LibraryRepository;
import com.example.libraryproject.service.AuthorService;
import com.example.libraryproject.service.LibraryService;
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
public class LibraryServiceTest {



    @InjectMocks
    private LibraryService libraryService;

    @Mock
    private BookDetailsRepository bookDetailsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private LibraryRepository libraryRepository;


//    @Test
//    @DisplayName("Runnning save Library in a happy flow!")
//    void saveNewLibraryHappyFlow(){
//
//        Library library1 = new Library("Library Location Test");
//
//        int librarianId1 = 1;
//        int bookId1 = 1;
//        int librarianId2 = 2;
//        int bookId2 = 2;
//
//
//        String librarianName = "Librarian Test";
//        Librarian librarian = new Librarian();
//        librarian.setLibrarianName(librarianName);
//
//        List<Librarian> librarians = new ArrayList<>();
//        librarians.add(librarian);
//
//        library1.setLibrarianList(librarians);
//
//
//        List<Integer> librarianIds = new ArrayList<>();
//        List<Integer> bookIds = new ArrayList<>();
//
//        librarianIds.add(librarianId1);
//        librarianIds.add(librarianId2);
//
//        bookIds.add(bookId1);
//        bookIds.add(bookId2);
//
//        when(libraryRepository.save(library1)).thenReturn(library1);
//
//        Library result = libraryService.saveLibrary(library1,bookIds,librarianIds);
//
//        //assert - check the result based on arrange and act
//
//        assertEquals(library1.getLocation(), result.getLocation());
//    }

    @Test
    @DisplayName("Runnning retrieve Library by name in a happy flow!")
    void retrieveLibraryByLocationHappyFlow(){
        String libraryLocation = "Location Test";
        Library library = new Library();
        library.setLocation(libraryLocation);

        when(libraryRepository.findAllByLocation(libraryLocation)).thenReturn(library);

        Library result  = libraryService.retrieveLibraryByLocation(libraryLocation);

        assertEquals(libraryLocation, result.getLocation());
    }

    @Test
    @DisplayName("Runnning retrieve Library by name in a negative flow!")
    void retrieveLibraryByNameNegativeFlow(){
        String location = "Location Test";
        Library library = new Library();
        library.setLocation(location);

        when(libraryRepository.findAllByLocation(location)).thenReturn(null);

        RuntimeException exception = assertThrows(NoLibraryFoundException.class,
                () -> libraryService.retrieveLibraryByLocation(location));

        assertEquals("No library with this location was found", exception.getMessage());
    }


    @Test
    @DisplayName("Runnning retrieve Librarys in a happy flow!")
    void retrieveLibrarysHappyFlow(){

        List<Library> librarys = new ArrayList<>();
        Library library1 = new Library("Library Location Test 1");
        Library library2 = new Library("Library Location Test 2");
        librarys.add(library1);
        librarys.add(library2);

        when(libraryRepository.findAll()).thenReturn(librarys);

        List<Library> result  = libraryService.retrieveLibrarys();

        assertEquals(library1.getLocation(), result.get(0).getLocation());
        assertEquals(library2.getLocation(), result.get(1).getLocation());

    }


    @Test
    @DisplayName("Runnning update Library Contact in a happy flow!")
    void updateLibraryLocationHappyFlow(){

        int libraryId = 1;
        String location = "Library Location Test";

        Library library = new Library();
        library.setLibraryId(libraryId);


        when(libraryRepository.findById(libraryId)).thenReturn(Optional.of(library));

        when(libraryRepository.save(library)).thenReturn(library);

        Library result = libraryService.updateLibraryLocation(libraryId,location);

        assertEquals(location, result.getLocation());

    }

    @Test
    @DisplayName("Runnning update Library Contact by name in a negative flow!")
    void updateLibraryLocationNegativeFlow(){

        int libraryId = 1;
        String location = "Library Location Test";


        Library library = new Library();
        library.setLibraryId(libraryId);

        java.util.Optional<Library> opt = Optional.empty();

        when(libraryRepository.findById(libraryId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoLibraryFoundException.class,
                () -> libraryService.updateLibraryLocation(libraryId,location));

        assertEquals("No library with this id was found", exception.getMessage());
    }

}
