package com.challenge.tinnova.tests.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.challenge.tinnova.DTO.VeiculosDTO;
import com.challenge.tinnova.entities.Veiculos;
import com.challenge.tinnova.repositories.VeiculosRepository;
import com.challenge.tinnova.services.VeiculosService;
import com.challenge.tinnova.services.exceptions.ResourceNotFoundException;
import com.challenge.tinnova.tests.factory.VeiculosFactory;


@ExtendWith(SpringExtension.class)
public class VeiculosServiceTests {

	@InjectMocks
	private VeiculosService service;
	
	@Mock
	private VeiculosRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private Veiculos veiculos;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		veiculos = VeiculosFactory.createVeiculos();
		
		doNothing().when(repository).deleteById(existingId);
		doThrow(EntityNotFoundException.class).when(repository).deleteById(nonExistingId);
		
		when(repository.findById(existingId)).thenReturn(Optional.of(veiculos));
		when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		when(repository.getById(existingId)).thenReturn(veiculos);
		doThrow(EntityNotFoundException.class).when(repository).getById(nonExistingId);
				
		when(repository.save(ArgumentMatchers.any())).thenReturn(veiculos);
	} 
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
			verify(repository, times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		verify(repository, times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void findByIdShouldReturnVeiculosDTOWhenIdExists() {
		VeiculosDTO result = service.findById(existingId);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	
	@Test
	public void updateShouldReturnVeiculosDTOWhenIdExists() {
		VeiculosDTO dto = new VeiculosDTO();
		VeiculosDTO result = service.update(existingId, dto);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		VeiculosDTO dto = new VeiculosDTO(); 
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, dto);
		});
	}
}
