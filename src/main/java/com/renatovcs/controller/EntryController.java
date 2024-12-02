package com.renatovcs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovcs.model.Entry;
import com.renatovcs.repository.EntryRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/entries")
@AllArgsConstructor
public class EntryController {

    private final EntryRepository entryRepository;
    
    @GetMapping
    public List<Entry> list() {
        return entryRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Entry> create(@RequestBody Entry entry) {
        //System.out.println(entry.getCategory());
        //return entryRepository.save(entry);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(entryRepository.save(entry));
    }
}
