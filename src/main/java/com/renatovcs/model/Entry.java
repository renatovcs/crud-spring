package com.renatovcs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity

public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 200)
    @Column(length = 250, nullable = false)
    private String description;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(length = 50)
    private String invoiceAccessKey;

    @Column(length = 50)
    private String company;

    @Positive
    @Column(nullable = false, precision = 15, scale = 2) 
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntryType type = EntryType.DEBIT;

    @Column(length = 3, nullable = false)
    private String currency = "BRL";
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime eventDate = LocalDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}

