package com.renatovcs;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.renatovcs.model.Entry;
import com.renatovcs.repository.EntryRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(EntryRepository entryRepository) {
		return args -> {
			entryRepository.deleteAll();
			
			Entry e = new Entry();
			e.setDescription("Conta de luz");
			e.setCategory("Gastos fixos");
			e.setAmount(BigDecimal.valueOf(100.00));

			entryRepository.save(e);
		};
	}
}
