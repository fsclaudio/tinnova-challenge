package com.challenge.tinnova.DTO;

public class VeiculosDecadaDTO {
	
	private int ano;
	private Long veiculo;
	
	public VeiculosDecadaDTO() {
		
	}

	public VeiculosDecadaDTO(int ano, Long veiculo) {
		this.ano = ano;
		this.veiculo = veiculo;
	}



	public int getAno() {
		return ano;
	}

	public Long getveiculo() {
		return veiculo;
	}

}
