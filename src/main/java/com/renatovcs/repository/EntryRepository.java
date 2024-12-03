package com.renatovcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renatovcs.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByDeletedAtIsNull();
}
