package com.example.libraryproject.service;

import com.example.libraryproject.exceptions.NoLibrarianFoundException;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.BookDetails;
import com.example.libraryproject.model.Librarian;
import com.example.libraryproject.model.Library;
import com.example.libraryproject.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {


    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;
    private final LibrarianRepository librarianRepository;

    public LibrarianService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository, AuthorRepository authorRepository, LibrarianRepository librarianRepository, LibraryRepository libraryRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.libraryRepository = libraryRepository;
        this.librarianRepository = librarianRepository;
    }

    public Librarian saveLibrarian(Librarian librarian, int libraryId){

        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new NoLibrarianFoundException("No librarian with this id was found"));

        librarian.setLibrary(library);
        return librarianRepository.save(librarian);
    }

    public List<Librarian> retrieveLibrarians () {
        return librarianRepository.findAll();
    }

    public Librarian retrieveLibrarianByName(String name) {
        Librarian librarian = librarianRepository.findAllByLibrarianName(name);

        if(librarian != null) return librarian;
        else throw new NoLibrarianFoundException("No librarian with this name was found");
    }

    public void deleteById(int librarianId){

        Librarian librarian = librarianRepository.findById(librarianId).orElseThrow(() ->new NoLibrarianFoundException("No librarian with this id was found"));


        librarianRepository.deleteById(librarianId);

    }

    public Librarian updateEmail(int librarianId, String email) {

        Librarian librarian = librarianRepository.findById(librarianId).orElseThrow(() ->new NoLibrarianFoundException("No librarian with this id was found"));

        librarian.setEmail(email);
        return  librarianRepository.save(librarian);

    }

    public Librarian retrieveLibrarianById(int librarianId) {
        return librarianRepository.findById(librarianId).orElseThrow(() -> new NoLibrarianFoundException("No librarian with this id was found"));
    }
}
