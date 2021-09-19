package com.challenge.tinnova.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.tinnova.DTO.VeiculosDecadaDTO;
import com.challenge.tinnova.DTO.VeiculosMarcaDTO;
import com.challenge.tinnova.entities.Veiculos;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {
	
	@Query("SELECT new com.challenge.tinnova.DTO.VeiculosMarcaDTO ( obj.marca, COUNT (obj.veiculo)) "
			+ " FROM Veiculos AS obj "  
			+ " GROUP BY obj.marca")
	List<VeiculosMarcaDTO> amountGroupedByMarca();
	
	@Query("SELECT new com.challenge.tinnova.DTO.VeiculosDecadaDTO ( obj.ano, COUNT (obj.veiculo)) "
			+ " FROM Veiculos AS obj "  
			+ " GROUP BY obj.ano")
	List<VeiculosDecadaDTO> amountGroupedByAno();
}
