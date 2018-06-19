package br.com.backend.requisitos.dto;

import br.com.backend.requisitos.entity.Integrante;

public class IntegranteDTO {
	
	private Integer id;
	
	private String nome;

	private String perfil;

	public IntegranteDTO() {}

	public IntegranteDTO(Integrante integrante) {
		this.id = integrante.getId();
		this.nome = integrante.getUsuario().getNome();
		this.perfil = integrante.getPerfilIntegranteProjeto().getValue();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}	
}
