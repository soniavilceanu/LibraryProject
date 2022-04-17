package com.example.libraryproject.controller;


import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Librarian;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.service.LibrarianService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/librarian")
@Validated
public class LibrarianController {

    public final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService){
        this.librarianService = librarianService;
    }


    @PostMapping("/new")
    public ResponseEntity<Librarian> saveLibrarian(@RequestBody @Valid Librarian librarian,
                                               @RequestParam int libraryId){
        return ResponseEntity.ok().body(librarianService.saveLibrarian(librarian, libraryId));
    }

    @GetMapping("/{librarianId}")
    public ResponseEntity<Librarian> retrieveLibrarianById(@PathVariable int librarianId){
        return ResponseEntity.ok().body(librarianService.retrieveLibrarianById(librarianId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Librarian>> retrieveLibrarians(){
        return ResponseEntity.ok().body(librarianService.retrieveLibrarians());
    }

    @GetMapping("/byName")
    public ResponseEntity<Librarian> retrieveLibrarianByName(@RequestParam String name){
        return ResponseEntity.ok().body(librarianService.retrieveLibrarianByName(name));
    }




    @PostMapping("/updateEmail/{librarianId}")
    public ResponseEntity<Librarian> updateEmail(@PathVariable int librarianId, @RequestParam @Valid String email){

        return ResponseEntity.ok().body(librarianService.updateEmail(librarianId, email));
    }


    @DeleteMapping("/deleteById/{librarianId}")
    public ResponseEntity<String> deleteLibrarianById(@PathVariable int librarianId){
        librarianService.deleteById(librarianId);
        return ResponseEntity.ok().body("librarian with id " + librarianId + " was deleted with success!!!");
    }
}
