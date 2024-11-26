package com.renatovcs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovcs.model.Entry;
import com.renatovcs.repository.EntryRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/entries")
@AllArgsConstructor
public class EntryController {

    private EntryRepository entryRepository;
    
    @GetMapping
    public List<Entry> list() {
        return entryRepository.findAll();
    }
}
