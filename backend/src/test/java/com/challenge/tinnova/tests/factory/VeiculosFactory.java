package com.challenge.tinnova.tests.factory;

import com.challenge.tinnova.DTO.VeiculosDTO;
import com.challenge.tinnova.entities.Veiculos;

public class VeiculosFactory {
	
	public static Veiculos createVeiculos() {
		return new Veiculos(1L, "BOLT", "CHEVROLET", 2022, "CHEVROLET BOLT ELETRIC 2022 ", false);
	}
	
	public static VeiculosDTO createVeiculosDTO() {
		return new VeiculosDTO(createVeiculos());
	}
	
	public static VeiculosDTO createVeiculosDTO(Long id) {
		VeiculosDTO dto = createVeiculosDTO();
		dto.setId(id);
		return dto;
	}
}