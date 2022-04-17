package com.example.libraryproject;

import com.example.libraryproject.model.*;
import com.example.libraryproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LibraryProjectApplication  implements CommandLineRunner {


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookDetailsRepository bookDetailsRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    public static void main(String[] args) {
        SpringApplication.run(LibraryProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Author author1 = new Author("Author1","0788445566");
        Author author2 = new Author("Author2","0799111122");
        Author author3 = new Author("Author3","0766345565");

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        Publisher publisher1 = new Publisher("Publisher1");
        Publisher publisher2 = new Publisher("Publisher2");

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        BookDetails bookDetails1 = new BookDetails("Book description 1",200, 30.50);
        BookDetails bookDetails2 = new BookDetails("Book description 2",450, 99.99);
        BookDetails bookDetails3 = new BookDetails("Book description 3",180, 50.00);
        BookDetails bookDetails4 = new BookDetails("Book description 4",290, 78.50);

        bookDetailsRepository.save(bookDetails1);
        bookDetailsRepository.save(bookDetails2);
        bookDetailsRepository.save(bookDetails3);
        bookDetailsRepository.save(bookDetails4);

        Book book1 = new Book("Book 1");
        Book book2 = new Book("Book 2");
        Book book3 = new Book("Book 3");
        Book book4 = new Book("Book 4");

        book1.setBookDetails(bookDetails1);
        book2.setBookDetails(bookDetails2);
        book3.setBookDetails(bookDetails3);
        book4.setBookDetails(bookDetails4);

        book1.setAuthor(author1);
        book2.setAuthor(author2);
        book3.setAuthor(author3);
        book4.setAuthor(author2);

        book1.setPublisher(publisher1);
        book2.setPublisher(publisher1);
        book3.setPublisher(publisher2);
        book4.setPublisher(publisher2);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);



        Library library = new Library("Iasi, str. Muresului");
        library.setBookList(Arrays.asList(book1,book2, book3, book4));
        //library.setLibrarianList(Arrays.asList(librarian1,librarian2));
        libraryRepository.save(library);


        Librarian librarian1 = new Librarian("Librarian1", "lib1@gmail.com");
        Librarian librarian2 = new Librarian("Librarian2", "lib2@yahoo.com");


        librarian1.setLibrary(library);
        librarian2.setLibrary(library);

        librarianRepository.save(librarian1);
        librarianRepository.save(librarian2);



    /*
     Artist artist1 = new Artist("Artist 1");
        Artist artist2 = new Artist("Artist 2");

        artistRepository.save(artist1);
        artistRepository.save(artist2);

        AlbumDetails albumDetails1 = new AlbumDetails("Some details for album1");
        AlbumDetails albumDetails2 = new AlbumDetails("Some details for album2");

        albumDetailsRepository.save(albumDetails1);
        albumDetailsRepository.save(albumDetails2);

        Album album1 = new Album("Album1", 5);
        Album album2 = new Album("Album2", 6);

        album1.setAlbumDetails(albumDetails1);
        album2.setAlbumDetails(albumDetails2);

        album1.setArtist(artist1);
        album2.setArtist(artist2);

        albumRepository.save(album1);
        albumRepository.save(album2);

        Shop shop = new Shop("Bucuresti sector 3");
        shop.setAlbumList(Arrays.asList(album1,album2));
        shopRepository.save(shop);
     */

    }
}
