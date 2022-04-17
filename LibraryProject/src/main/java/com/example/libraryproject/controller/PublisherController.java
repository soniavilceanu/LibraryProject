package com.example.libraryproject.controller;


import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Book;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publisher")
@Validated
public class PublisherController {




    public final PublisherService publisherService;

    public  PublisherController(PublisherService publisherService){
        this.publisherService = publisherService;
    }


    @PostMapping("/new")
    public ResponseEntity<Publisher> savePublisher(@RequestBody @Valid Publisher publisher){
        return ResponseEntity.ok()
                .body(publisherService.saveNewPublisher(publisher));
    }


    @GetMapping("/byName")
    public ResponseEntity<Publisher> retrieveByName(@RequestParam String publisherName){
        return ResponseEntity.ok().body(publisherService.retrievePublisherByName(publisherName));
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<Publisher> retrieveById(@PathVariable int publisherId){
        return ResponseEntity.ok().body(publisherService.retrievePublisherById(publisherId));
    }

    @PostMapping("/updateName/{publisherId}")
    public ResponseEntity<Publisher> updatePublisherName(@PathVariable int publisherId, @RequestParam @Valid String publisherName){

        return ResponseEntity.ok().body(publisherService.updatePublisherName(publisherId, publisherName));
    }



    @GetMapping("/list")
    public ResponseEntity<List<Publisher>> retrievePublishers(){
        return ResponseEntity.ok().body(publisherService.retrievePublishers());
    }

}
