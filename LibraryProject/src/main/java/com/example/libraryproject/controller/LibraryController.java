package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Library;
import com.example.libraryproject.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
@Validated
public class LibraryController {

    public final LibraryService libraryService;

    public  LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }


    @PostMapping("/new")
    public ResponseEntity<Library> savelibrary(@RequestBody @Valid Library library,
                                               @RequestParam List<Integer> bookIds,
                                                 @RequestParam List<Integer> librarianIds){
        return ResponseEntity.ok().body(libraryService.saveLibrary(library, bookIds, librarianIds));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Library>> retrieveLibrarys(){
        return ResponseEntity.ok().body(libraryService.retrieveLibrarys());
    }

    @GetMapping("/byLocation")
    public ResponseEntity<Library> retrievelibrarybyLocation(@RequestParam String location){
        return ResponseEntity.ok().body(libraryService.retrieveLibraryByLocation(location));
    }

    @PostMapping("/updateLocation/{libraryId}")
    public ResponseEntity<Library> updateLibraryLocation(@PathVariable int libraryId, @RequestParam String location){

        return ResponseEntity.ok().body(libraryService.updateLibraryLocation(libraryId, location));
    }



    @DeleteMapping("/deleteById/{libraryId}")
    public ResponseEntity<String> deleteLibraryById(@PathVariable int libraryId){
        libraryService.deleteById(libraryId);
        return ResponseEntity.ok().body("Library with id " + libraryId + " was deleted with success!!!");
    }
}
