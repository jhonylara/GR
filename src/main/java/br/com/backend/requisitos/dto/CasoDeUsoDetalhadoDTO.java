package br.com.backend.requisitos.dto;

import java.util.Calendar;

import br.com.backend.requisitos.entity.CasoDeUso;

public class CasoDeUsoDetalhadoDTO {

	private Integer id;

	private String nome;
	
	private String escopo;
	
	private String nivel;
	
	private String preCondicao;
	
	private Calendar dataInclusao;
	
	private Calendar dataAlteracao;
	
	private ProjetoDTO projetoDTO;

	private IntegranteDTO integranteDTO;
	

	public CasoDeUsoDetalhadoDTO() {}
	
	public CasoDeUsoDetalhadoDTO(CasoDeUso casoDeUso) {
		this.id = casoDeUso.getId();
		this.nome = casoDeUso.getNome();
		this.escopo = casoDeUso.getEscopo();
		this.nivel = casoDeUso.getNivel();
		this.preCondicao = casoDeUso.getPreCondicao();
		this.dataInclusao = casoDeUso.getDataInclusao();
		this.dataAlteracao =  casoDeUso.getDataAlteracao();
		this.projetoDTO = new ProjetoDTO(casoDeUso.getProjeto());
		this.integranteDTO = new IntegranteDTO(casoDeUso.getIntegrante());	
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
	
	public ProjetoDTO getProjetoDTO() {
		return projetoDTO;
	}

	public void setProjetoDTO(ProjetoDTO projetoDTO) {
		this.projetoDTO = projetoDTO;
	}

	public IntegranteDTO getIntegranteDTO() {
		return integranteDTO;
	}

	public void setIntegranteDTO(IntegranteDTO integranteDTO) {
		this.integranteDTO = integranteDTO;
	}

}
