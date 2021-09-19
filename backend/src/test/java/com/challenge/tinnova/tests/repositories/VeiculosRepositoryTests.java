package com.challenge.tinnova.tests.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.challenge.tinnova.entities.Veiculos;
import com.challenge.tinnova.repositories.VeiculosRepository;
import com.challenge.tinnova.tests.factory.VeiculosFactory;


@DataJpaTest
public class VeiculosRepositoryTests {
	
	@Autowired
	private VeiculosRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countTotalVeiculos;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalVeiculos = 24L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Veiculos veiculos = VeiculosFactory.createVeiculos();
		veiculos.setId(null);
		
		veiculos = repository.save(veiculos);
		Optional<Veiculos> result = repository.findById(veiculos.getId());
		
		Assertions.assertNotNull(veiculos.getId());
		Assertions.assertEquals(countTotalVeiculos + 1L, veiculos.getId());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertSame(result.get(), veiculos);
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		Optional<Veiculos> result = repository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
}
