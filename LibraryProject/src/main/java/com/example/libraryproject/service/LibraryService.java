package com.example.libraryproject.service;

import com.example.libraryproject.exceptions.NoLibraryFoundException;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Librarian;
import com.example.libraryproject.model.Library;
import com.example.libraryproject.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;
    private final LibrarianRepository librarianRepository;

    public LibraryService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository, AuthorRepository authorRepository, LibraryRepository libraryRepository, LibrarianRepository librarianRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.libraryRepository = libraryRepository;
        this.librarianRepository = librarianRepository;
    }

        public Library saveLibrary(Library library, List <Integer> bookIds, List <Integer> librarianIds){
            List<Book> books = bookRepository.findAllById(bookIds);
            List<Librarian> librarians = librarianRepository.findAllById(librarianIds);
            library.setBookList(books);
            return libraryRepository.save(library);
        }

        public List<Library> retrieveLibrarys () {
            return libraryRepository.findAll();
        }

    public Library retrieveLibraryByLocation(String location) {
        Library library = libraryRepository.findAllByLocation(location);

        if(library != null) return library;
        else throw new NoLibraryFoundException("No library with this location was found");
    }

    public void deleteById(int libraryId){
        libraryRepository.deleteById(libraryId);

    }

    public Library updateLibraryLocation(int libraryId, String location) {

        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new NoLibraryFoundException("No library with this id was found"));
        library.setLocation(location);
        return libraryRepository.save(library);
    }
}

