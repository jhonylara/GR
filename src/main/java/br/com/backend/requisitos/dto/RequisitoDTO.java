package br.com.backend.requisitos.dto;

import br.com.backend.requisitos.entity.Requisito;

public class RequisitoDTO {

	private Integer id;
	
	private String descricao;
	
	private String importancia;
	
	private String fonte;
	
	private String categoria;
	
	public RequisitoDTO() {}
	
	public RequisitoDTO(Requisito requisito) {
		this.id = requisito.getId();
		this.descricao = requisito.getDescricao();
		this.importancia = requisito.getImportancia();
		this.fonte = requisito.getFonte();
		this.categoria = requisito.getCategoria().getValue();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImportancia() {
		return importancia;
	}

	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
