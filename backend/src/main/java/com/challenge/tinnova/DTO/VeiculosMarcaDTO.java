package com.challenge.tinnova.DTO;

public class VeiculosMarcaDTO {
	private String marca;
	private int totalVeiculo;
	
	public VeiculosMarcaDTO() {	
	}

	public VeiculosMarcaDTO(String marca, int totalVeiculo) {
		this.marca = marca;
		this.totalVeiculo = totalVeiculo;
	}

	public String getMarca() {
		return marca;
	}

	public int getTotalVeiculo() {
		return totalVeiculo;
	}
	
}
