package com.challenge.tinnova.DTO;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.challenge.tinnova.entities.Veiculos;

public class VeiculosDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	@NotBlank(message = "Informe o nome do veiculo!")
	private String veiculo;
	private String marca;
	
	@NotNull(message = "Informe o ano do Veiculo!")
	@Digits(integer = 4, fraction = 0, message =  "Informe o ano do veiculo com 4 digtos!")
	private Integer ano;
	
	@NotNull
	@NotBlank(message = "Informe o uma descrição para o veiculo!")
    private String descricao;
	
	private Boolean vendido;
	
	public VeiculosDTO() {
	}

	public VeiculosDTO(Long id, @NotNull @NotBlank(message = "Informe o nome do veiculo!") String veiculo, 
			String marca,@NotNull(message = "Informe o ano do Veiculo!") @Digits(integer = 4, fraction = 0, message = "Informe o ano do veiculo com 4 digtos!") Integer ano,
			@NotNull @NotBlank(message = "Informe o uma descrição para o veiculo!") String descricao, Boolean vendido) {
		this.id = id;
		this.veiculo = veiculo;
		this.marca = marca;
		this.ano = ano;
		this.descricao = descricao;
		this.vendido = vendido;
	}
	
	public VeiculosDTO(Veiculos entity) {
	
		id      = entity.getId();
		veiculo = entity.getVeiculo();
		marca   = entity.getMarca();
		ano     = entity.getAno();
		descricao = entity.getDescricao();
		vendido = entity.getVendido();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}
	
	
	
}
