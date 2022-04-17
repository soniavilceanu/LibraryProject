package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.service.AuthorService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {


    public final AuthorService authorService;

    public  AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }


    @PostMapping("/new")
    public ResponseEntity<Author> saveAuthor(@RequestBody @Valid Author author){
        return ResponseEntity.ok()
                .body(authorService.saveNewAuthor(author));
    }


    @GetMapping("/byName")
    public ResponseEntity<Author> retrieveByName(@RequestParam String authorName){
        return ResponseEntity.ok().body(authorService.retrieveAuthorByName(authorName));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> retrieveById(@PathVariable int authorId){
        return ResponseEntity.ok().body(authorService.retrieveAuthorById(authorId));
    }


    @PostMapping("/updateContact/{authorId}")
    public ResponseEntity<Author> updateContact(@PathVariable int authorId, @RequestParam @Valid String contact){

        return ResponseEntity.ok().body(authorService.updateContact(authorId, contact));
    }

    @PostMapping("/updateName/{authorId}")
    public ResponseEntity<Author> updateAuthorName(@PathVariable int authorId, @RequestParam @Valid String authorName){

        return ResponseEntity.ok().body(authorService.updateAuthorName(authorId, authorName));
    }


//    @DeleteMapping("/deleteById/{authorId}")
//    public ResponseEntity<String> deleteAuthorById(@PathVariable int authorId){
//        authorService.deleteById(authorId);
//        return ResponseEntity.ok().body("author with id " + authorId + " was deleted with success!!!");
//    }

    @GetMapping("/list")
    public ResponseEntity<List<Author>> retrieveAuthors(){
        return ResponseEntity.ok().body(authorService.retrieveAuthors());
    }

    @GetMapping("/{authorId}/listBooks")
    public ResponseEntity<List<Book>> retrieveBooks(@PathVariable int authorId){
        return ResponseEntity.ok().body(authorService.retrieveBooks(authorId));
    }





}
