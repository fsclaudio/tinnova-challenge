package com.challenge.tinnova.DTO;

public class VeiculosDecadaDTO {
	
	private int ano;
	private int totalVeiculo;
	
	public VeiculosDecadaDTO() {
		
	}

	public VeiculosDecadaDTO(int ano, int totalVeiculo) {
		this.ano = ano;
		this.totalVeiculo = totalVeiculo;
	}

	public int getAno() {
		return ano;
	}

	public int getTotalVeiculo() {
		return totalVeiculo;
	}

}
