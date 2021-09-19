package com.challenge.tinnova.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.tinnova.DTO.VeiculosDTO;
import com.challenge.tinnova.DTO.VeiculosDecadaDTO;
import com.challenge.tinnova.DTO.VeiculosMarcaDTO;
import com.challenge.tinnova.entities.Veiculos;
import com.challenge.tinnova.repositories.VeiculosRepository;
import com.challenge.tinnova.services.exceptions.ResourceNotFoundException;


@Service
public class VeiculosService {

	@Autowired
	public VeiculosRepository repository;
	

	@Transactional(readOnly = true)
	public Page<VeiculosDTO> findAllPaged(PageRequest pageRequest) {
		Page<Veiculos> list = repository.findAll(pageRequest);
		return list.map(x -> new VeiculosDTO(x));
	}
	
	@Transactional(readOnly = true)
	public VeiculosDTO findById(Long id) {
		Optional<Veiculos> obj = repository.findById(id);
		Veiculos entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		
		return new VeiculosDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<VeiculosMarcaDTO> amountGroupedMarca(){
		
		return repository.amountGroupedByMarca();
	}
	
	@Transactional(readOnly = true)
	public List<VeiculosDecadaDTO> amountGroupedDecada(){
		
		return repository.amountGroupedByAno();
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
