package br.com.backend.requisitos.dto;

import java.util.Calendar;

import br.com.backend.requisitos.entity.CasoDeUso;

public class CasoDeUsoDTO {

	private Integer id;

	private String nome;
	
	private String escopo;
	
	private String nivel;
	
	private String preCondicao;
	
	private Calendar dataInclusao;
	
	private Calendar dataAlteracao;
	
	public CasoDeUsoDTO() {}
	
	public CasoDeUsoDTO(CasoDeUso casoDeUso) {
		this.id = casoDeUso.getId();
		this.nome = casoDeUso.getNome();
		this.escopo = casoDeUso.getEscopo();
		this.nivel = casoDeUso.getNivel();
		this.preCondicao = casoDeUso.getPreCondicao();
		this.dataInclusao = casoDeUso.getDataInclusao();
		this.dataAlteracao =  casoDeUso.getDataAlteracao();
				
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

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getPreCondicao() {
		return preCondicao;
	}

	public void setPreCondicao(String preCondicao) {
		this.preCondicao = preCondicao;
	}

	public Calendar getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Calendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	
}
