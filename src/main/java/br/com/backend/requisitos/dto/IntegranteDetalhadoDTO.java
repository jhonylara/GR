package br.com.backend.requisitos.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

public class IntegranteDetalhadoDTO {

	private Integer id;
	
	private String nome;

	private String perfil;
	
	private List<ProjetoDTO> projetos;

	public IntegranteDetalhadoDTO() {}

	public IntegranteDetalhadoDTO(Integrante integrante) {
		this.id = integrante.getId();
		this.nome = integrante.getUsuario().getNome();
		this.perfil = integrante.getPerfilIntegranteProjeto().getValue();
		this.projetos = this.getProjetosDTO(integrante.getProjetos());
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

	public List<ProjetoDTO> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<ProjetoDTO> projetos) {
		this.projetos = projetos;
	}
	
	private List<ProjetoDTO> getProjetosDTO(List<Projeto> projetos) {
		List<ProjetoDTO> projetoDTOs = new ArrayList<ProjetoDTO>();
		for (Projeto projeto : projetos) 
			projetoDTOs.add(new ProjetoDTO(projeto));

		return projetoDTOs;
	}
}
