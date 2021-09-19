package com.challenge.tinnova.DTO;

public class VeiculosMarcaDTO {
	private String marca;
	private Long veiculo;
	
	public VeiculosMarcaDTO() {	
	}

	public VeiculosMarcaDTO(String marca, Long veiculo) {
	
		this.marca = marca;
		this.veiculo = veiculo;
	}


	public String getMarca() {
		return marca;
	}

	public Long getVeiculo() {
		return veiculo;
	}

	
	
}
