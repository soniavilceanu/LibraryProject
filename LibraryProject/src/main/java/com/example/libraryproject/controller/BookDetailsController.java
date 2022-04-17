package com.example.libraryproject.controller;


import com.example.libraryproject.model.BookDetails;
import com.example.libraryproject.service.BookDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookDetails")
@Validated
public class BookDetailsController {

    public final BookDetailsService bookDetailsService;

    public  BookDetailsController(BookDetailsService bookDetailsService){
        this.bookDetailsService = bookDetailsService;
    }


    @PostMapping("/new")
    public ResponseEntity<BookDetails> saveBookDetails(@RequestBody @Valid BookDetails bookDetails){
        return ResponseEntity.ok()
                .body(bookDetailsService.saveNewBookDetails(bookDetails));
    }


    @GetMapping("/{bookDetailsId}")
    public ResponseEntity<BookDetails> retrieveByName(@PathVariable int bookDetailsId){
        return ResponseEntity.ok().body(bookDetailsService.retrieveBookDetailsById(bookDetailsId));
    }

//    @GetMapping("/byMaxNoOfPages/{noOfPages}")
//    public ResponseEntity<List<BookDetails>> retrieveByMaxNoOfPages(@PathVariable int noOfPages){
//        return ResponseEntity.ok().body(bookDetailsService.retrieveBookDetailsByMaxNoOfPages(noOfPages));
//    }


    @GetMapping("/list")
    public ResponseEntity<List<BookDetails>> retrieveBookDetailss(){
        return ResponseEntity.ok().body(bookDetailsService.retrieveBookDetails());
    }

}
