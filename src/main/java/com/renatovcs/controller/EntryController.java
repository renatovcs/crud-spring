package com.renatovcs.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renatovcs.model.Entry;
import com.renatovcs.repository.EntryRepository;
import com.renatovcs.service.EntryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/api/entries")
public class EntryController {

    private final EntryRepository entryRepository;
    private final EntryService entryService;

    public EntryController(EntryRepository entryRepository, EntryService entryService) {
        this.entryRepository = entryRepository;
        this.entryService = entryService;
    }
    
    @GetMapping
    public List<Entry> list() {
        return entryService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> findById(@PathVariable("id") @NotNull @Positive Long id) {
        return entryRepository.findById(id)
            .map(data -> ResponseEntity.ok().body(data))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping()
    public ResponseEntity<Entry> create(@RequestBody @Valid Entry entry) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(entryRepository.save(entry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> update(@PathVariable Long id, @RequestBody @Valid Entry entry) {
        return entryRepository.findById(id)
            .map(data -> {
                data.setAmount(entry.getAmount());
                data.setCategory(entry.getCategory());
                data.setCurrency(entry.getCurrency());
                data.setDescription(entry.getDescription());
                data.setEventDate(entry.getEventDate());
                data.setType(entry.getType());
                data.setUpdatedAt(LocalDateTime.now());
                Entry updated = entryRepository.save(data);

                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entry> delete(@PathVariable Long id) {
        
        return entryRepository.findById(id)
        .map(data -> {
            data.setDeletedAt(LocalDateTime.now());
            Entry updated = entryRepository.save(data);
            //entryRepository.deleteById(id)
            return ResponseEntity.ok().body(updated);
            //return ResponseEntity.noContent().build()
        })
        .orElse(ResponseEntity.notFound().build());
    }
}
