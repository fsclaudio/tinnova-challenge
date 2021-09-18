package com.challenge.tinnova.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.tinnova.DTO.VeiculosDTO;
import com.challenge.tinnova.services.VeiculosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/veiculos")
@Api(value = "API REST Veículos")
@CrossOrigin(origins = "*")
public class VeiculosController {
	
	@Autowired
	private VeiculosService service;
	
	@GetMapping
	@ApiOperation(value = "Retorna todos os veiculos paginado")
	public ResponseEntity<Page<VeiculosDTO>> findAllPage(
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "marca",defaultValue = "") String marca,
			@RequestParam(value = "orderBy",defaultValue = "veiculo") String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC") String direction
			){
		
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,org.springframework.data.domain.Sort.Direction.valueOf(direction),orderBy);
		
		Page<VeiculosDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna um veiculos")
	public ResponseEntity<VeiculosDTO> findById(@PathVariable Long id){
		VeiculosDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	@ApiOperation(value = "Salva um novo veículo")
	public ResponseEntity<VeiculosDTO> insert(@Valid @Range VeiculosDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza um veículo por id")
	public ResponseEntity<VeiculosDTO> update(@PathVariable Long id, @Valid @RequestBody VeiculosDTO dto) {
		dto = service.update(id, dto);		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleta um veículo por id")
	public ResponseEntity<VeiculosDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
