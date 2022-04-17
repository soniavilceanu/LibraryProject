package com.example.libraryproject.Service;

import com.example.libraryproject.exceptions.NoAuthorFoundException;
import com.example.libraryproject.exceptions.NoPublisherFoundException;
import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.repository.*;
import com.example.libraryproject.service.BookService;
import com.example.libraryproject.service.PublisherService;
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
public class PublisherServiceTest {

    @InjectMocks
    private PublisherService publisherService;

    @Mock
    private BookDetailsRepository bookDetailsRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private LibraryRepository libraryRepository;
    @Mock
    private PublisherRepository publisherRepository;



    @Test
    @DisplayName("Runnning save publisher in a happy flow!")
    void saveNewPublisherHappyFlow(){
        
        Publisher publisher = new Publisher("Publisher Test");

        when(publisherRepository.save(publisher)).thenReturn(publisher);


        Publisher result = publisherService.saveNewPublisher(publisher);

        assertEquals(publisher.getPublisherName(), result.getPublisherName());
    }


    @Test
    @DisplayName("Runnning retrieve Publisher by name in a happy flow!")
    void retrievePublisherByNameHappyFlow(){
        String publisherName = "Publisher Test";
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherName);

        when(publisherRepository.findPublisherByPublisherName(publisherName)).thenReturn(publisher);

        Publisher result  = publisherService.retrievePublisherByName(publisherName);

        assertEquals(publisherName, result.getPublisherName());
    }

    @Test
    @DisplayName("Runnning retrieve Publisher by name in a negative flow!")
    void retrievePublisherByNameNegativeFlow(){
        String publisherName = "Publisher Test";
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherName);

        when(publisherRepository.findPublisherByPublisherName(publisherName)).thenReturn(null);

        RuntimeException exception = assertThrows(NoPublisherFoundException.class,
                () -> publisherService.retrievePublisherByName(publisherName));

        assertEquals("No publisher with this name was found", exception.getMessage());
    }


    @Test
    @DisplayName("Runnning retrieve Publishers in a happy flow!")
    void retrievePublishersHappyFlow(){

        List<Publisher> publishers = new ArrayList<>();
        Publisher publisher1 = new Publisher("Publisher Test 1");
        Publisher publisher2 = new Publisher("Publisher Test 2");
        publishers.add(publisher1);
        publishers.add(publisher2);

        when(publisherRepository.findAll()).thenReturn(publishers);

        List<Publisher> result  = publisherService.retrievePublishers();

        assertEquals(publisher1.getPublisherName(), result.get(0).getPublisherName());
        assertEquals(publisher2.getPublisherName(), result.get(1).getPublisherName());
        assertEquals(publisher1.getPublisherName(), result.get(0).getPublisherName());
        assertEquals(publisher2.getPublisherName(), result.get(1).getPublisherName());
    }



    @Test
    @DisplayName("Runnning update Publisher Contact in a happy flow!")
    void updatePublisherNameHappyFlow(){

        int publisherId = 1;
        String publisherName = "Publisher Name Test";

        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherId);


        when(publisherRepository.findById(publisherId)).thenReturn(Optional.of(publisher));

        when(publisherRepository.save(publisher)).thenReturn(publisher);

        Publisher result = publisherService.updatePublisherName(publisherId,publisherName);

        assertEquals(publisherName, result.getPublisherName());

    }

    @Test
    @DisplayName("Runnning update Publisher Contact by name in a negative flow!")
    void updatePublisherNameNegativeFlow(){

        int publisherId = 1;
        String publisherName = "Publisher Name Test";


        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherId);

        java.util.Optional<Publisher> opt = Optional.empty();

        when(publisherRepository.findById(publisherId)).thenReturn(opt);


        RuntimeException exception = assertThrows(NoPublisherFoundException.class,
                () -> publisherService.updatePublisherName(publisherId,publisherName));

        assertEquals("No publisher with this id was found", exception.getMessage());
    }

    @Test
    @DisplayName("Runnning retrieve Publisher by Id in a happy flow!")
    void retrievePublisherByIdHappyFlow(){
        int publisherId = 1;
        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherId);

        when(publisherRepository.findById(publisherId)).thenReturn(Optional.of(publisher));

        Publisher result  = publisherService.retrievePublisherById(publisherId);

        assertEquals(publisher.getPublisherId(), result.getPublisherId());
    }

    @Test
    @DisplayName("Runnning retrieve Publisher by Id in a negative flow!")
    void retrievePublisherByIdNegativeFlow(){
        int publisherId = 1;
        Publisher publisher = new Publisher();
        publisher.setPublisherId(publisherId);

        java.util.Optional<Publisher> opt = Optional.empty();


        when(publisherRepository.findById(publisherId)).thenReturn(opt);

        RuntimeException exception = assertThrows(NoPublisherFoundException.class,
                () -> publisherService.retrievePublisherById(publisherId));

        assertEquals("No publisher with this id was found", exception.getMessage());
    }


}
