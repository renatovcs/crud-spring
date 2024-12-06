package com.renatovcs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.renatovcs.model.Entry;
import com.renatovcs.repository.EntryRepository;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> list() {
        return entryRepository.findByDeletedAtIsNull();
    }
    
}
