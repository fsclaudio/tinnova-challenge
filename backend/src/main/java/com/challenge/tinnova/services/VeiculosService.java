package com.challenge.tinnova.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.tinnova.DTO.VeiculosDTO;
import com.challenge.tinnova.entities.Veiculos;
import com.challenge.tinnova.repositories.VeiculosRepository;
import com.challenge.tinnova.services.exceptions.ResourceNotFoundException;


@Service
public class VeiculosService {

	@Autowired
	public VeiculosRepository repository;
	
	@Transactional(readOnly = true)
	public List<VeiculosDTO> findAll() {
		List<Veiculos> list = repository.findAll();
		return list.stream().map(x -> new VeiculosDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public VeiculosDTO findById(Long id) {
		Optional<Veiculos> obj = repository.findById(id);
		Veiculos entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		
		return new VeiculosDTO(entity);
	}
	
	@Transactional
	public VeiculosDTO insert(VeiculosDTO dto) {
	  Veiculos veiculos = new Veiculos();
	  copyDtoToEntity(dto, veiculos);
	  veiculos = repository.save(veiculos);
	  
	  return new VeiculosDTO(veiculos);
	}
	
	@Transactional
	public VeiculosDTO update(Long id, VeiculosDTO dto) {
		try {
			 Veiculos entity = repository.getById(id);
			 copyDtoToEntity(dto, entity);
			 return new VeiculosDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity " + id + " not found");
		}  
	  
	}
	
	public void delete(Long id) {
		try {
			 repository.deleteById(id);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Entity " + id + " not found");
		}  	
	}
	
	
private void copyDtoToEntity(VeiculosDTO dto, Veiculos entity) {
		
		entity.setVeiculo(dto.getVeiculo());
		entity.setMarca(dto.getMarca());
		entity.setAno(dto.getAno());
		entity.setDescricao(dto.getDescricao());
		entity.setVendido(dto.getVendido());
		
	}
}
