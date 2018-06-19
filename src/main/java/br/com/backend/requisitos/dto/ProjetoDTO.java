package br.com.backend.requisitos.dto;

import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.enums.PerfilIntegranteProjeto;

public class ProjetoDTO {
	
	private Integer id;

	private String nome;
	
	private Calendar dataInicio;

	private Calendar dataFim;
	
	private String criadoProjeto; 

	public ProjetoDTO() {}
	
	public ProjetoDTO(Projeto projeto) {
		this.id = projeto.getId();
		this.nome = projeto.getNome();
		this.dataInicio = projeto.getDataInicio();
		this.dataFim = projeto.getDataFim();
		this.criadoProjeto = this.getCriadorPerfil(projeto.getIntegrantes());
	}
	
	private String getCriadorPerfil(List<Integrante> integrantes) {
		String nomeIntegranteGerente = null;
		for (Integrante integrante : integrantes)
			if(integrante.getPerfilIntegranteProjeto() == PerfilIntegranteProjeto.GERENTE) 
				return nomeIntegranteGerente = integrante.getUsuario().getNome();

		return nomeIntegranteGerente;
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

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public String getCriadoProjeto() {
		return criadoProjeto;
	}

	public void setCriadoProjeto(String criadoProjeto) {
		this.criadoProjeto = criadoProjeto;
	}
}
