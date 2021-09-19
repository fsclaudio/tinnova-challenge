package com.challenge.tinnova.tests.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.challenge.tinnova.DTO.VeiculosDTO;
import com.challenge.tinnova.services.VeiculosService;
import com.challenge.tinnova.services.exceptions.DatabaseException;
import com.challenge.tinnova.services.exceptions.ResourceNotFoundException;
import com.challenge.tinnova.tests.factory.VeiculosFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class VeiculosControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VeiculosService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private VeiculosDTO newVeiculosDTO;
	private VeiculosDTO existingVeiculosDTO;
	private PageImpl<VeiculosDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 3L;
		
		newVeiculosDTO = VeiculosFactory.createVeiculosDTO();
		existingVeiculosDTO = VeiculosFactory.createVeiculosDTO(existingId);
		
		page = new PageImpl<>(List.of(existingVeiculosDTO));
		
		when(service.findById(existingId)).thenReturn(existingVeiculosDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.findAllPaged(any())).thenReturn(page);
		
		when(service.insert(any())).thenReturn(existingVeiculosDTO);
		
		when(service.update(eq(existingId), any())).thenReturn(existingVeiculosDTO);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
		
		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);
	}
	
	@Test
	public void findByIdShouldReturnVeiculosDTOWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/veiculos/{id}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(existingId));
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(get("/veiculos/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findAllShouldReturnList() throws Exception {
		ResultActions result = mockMvc.perform(get("/veiculos").accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void insertShouldReturnCreatedVeiculosDTOWhenValidData() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newVeiculosDTO);
		
		ResultActions result = mockMvc.perform(post("/veiculos")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		
	}
	
	@Test
	public void insertShouldReturnUnprocessableEntityWhenVeiculosNameIsEmpty() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(existingVeiculosDTO);
		
		ResultActions result = mockMvc.perform(post("/veiculos")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void updateShouldReturnVeiculosDTOWhenIdExists() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newVeiculosDTO);
		
		ResultActions result = mockMvc.perform(put("/veiculos/{id}", existingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(existingId));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newVeiculosDTO);
		ResultActions result = mockMvc.perform(put("/veiculos/{id}", nonExistingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(delete("/veiculos/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		ResultActions result = mockMvc.perform(delete("/veiculos/{id}", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
}