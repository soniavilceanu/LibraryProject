package com.example.libraryproject.controller;

import com.example.libraryproject.model.*;
import com.example.libraryproject.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

    
    public final BookService bookService;

    public  BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/new")
    public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book,
                                           @RequestParam int bookDetailsId,
                                           @RequestParam int authorId,
                                           @RequestParam int publisherId){
        return ResponseEntity.ok()
                .body(bookService.saveBook(book, bookDetailsId, authorId, publisherId));
    }

    @PostMapping("/updatePublisher/{bookId}")
    public ResponseEntity<Book> updateBookPublisher(@PathVariable int bookId, @RequestParam int publisherId){
        return ResponseEntity.ok().body(bookService.updateBookPublisher(bookId, publisherId));
    }

    @PostMapping("/updateName/{bookId}")
    public ResponseEntity<Book> updateBookName(@PathVariable int bookId, @RequestParam @Valid String bookName){
        return ResponseEntity.ok().body(bookService.updateBookName(bookId, bookName));
    }

    @PostMapping("/updateNoOfPages/{bookId}/{noOfPages}")
    public ResponseEntity<Book> updateNoOfPages(@PathVariable int bookId, @PathVariable @Valid int noOfPages){
        return ResponseEntity.ok().body(bookService.updateNoOfPages(bookId, noOfPages));
    }

    @PostMapping("/updatePrice/{bookId}/{price}")
    public ResponseEntity<Book> updatePrice(@PathVariable int bookId, @PathVariable @Valid double price){
        return ResponseEntity.ok().body(bookService.updatePrice(bookId, price));
    }

    @PostMapping("/updateDescription/{bookId}/{description}")
    public ResponseEntity<Book> updateDescription(@PathVariable int bookId, @PathVariable String description){
        return ResponseEntity.ok().body(bookService.updateDescription(bookId, description));
    }

    @GetMapping("/byName")
    public ResponseEntity<Book> retrieveByName(@RequestParam String bookName){
        return ResponseEntity.ok().body(bookService.retrieveBookByName(bookName));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> retrieveById(@PathVariable int bookId){
        return ResponseEntity.ok().body(bookService.retrieveBookById(bookId));
    }

    @GetMapping("/byMaxNoOfPages/{noOfPages}")
    public ResponseEntity<List<Book>> retrieveByMaxNoOfPages(@PathVariable int noOfPages){
        return ResponseEntity.ok().body(bookService.retrieveBooksByMaxNoOfPages(noOfPages));
    }

    @GetMapping("/byMaxPrice/{price}")
    public ResponseEntity<List<Book>> retrieveByMaxPrice(@PathVariable double price){
        return ResponseEntity.ok().body(bookService.retrieveBooksByMaxPrice(price));
    }


    @GetMapping("/list")
    public ResponseEntity<List<Book>> retrieveBooks(){
        return ResponseEntity.ok().body(bookService.retrieveBooks());
    }


}
