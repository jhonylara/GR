package br.com.backend.requisitos.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.Integrante;

public class AtividadeDetalhadaDTO {
	
	private String nome;
	
	private String descricao;

	private String status;
	
	private Calendar dataInicio;
	
	private Calendar dataFim;
	
	private Calendar dataConclusao;
	
	private RequisitoDTO requisito;
	
	private IntegranteDTO criadoPor;
	
	private List<IntegranteDTO> desenvolvidos;
	
	public AtividadeDetalhadaDTO() {}
	
	public AtividadeDetalhadaDTO(Atividade atividade) {
		this.nome = atividade.getNome();
		this.descricao = atividade.getDescricao();
		this.status = atividade.getStatus().getValue();
		this.dataInicio = atividade.getDataInicio();
		this.dataFim = atividade.getDataFim();
		this.dataConclusao = atividade.getDataConclusao();
		this.requisito = new RequisitoDTO(atividade.getRequisito());
		this.criadoPor = new IntegranteDTO(atividade.getCriador());
		this.desenvolvidos = this.listaIntegrantesDesenvolvedores(atividade.getDesenvolvedores());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Calendar getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Calendar dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public RequisitoDTO getRequisito() {
		return requisito;
	}

	public void setRequisito(RequisitoDTO requisito) {
		this.requisito = requisito;
	}

	public IntegranteDTO getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(IntegranteDTO criadoPor) {
		this.criadoPor = criadoPor;
	}

	public List<IntegranteDTO> getDesenvolvidos() {
		return desenvolvidos;
	}

	public void setDesenvolvidos(List<IntegranteDTO> desenvolvidos) {
		this.desenvolvidos = desenvolvidos;
	}
	
	private List<IntegranteDTO> listaIntegrantesDesenvolvedores(List<Integrante> integrantes) {
		if(integrantes.isEmpty()) return null;

		List<IntegranteDTO> desenvolvedores = new ArrayList<IntegranteDTO>();
		for (Integrante integrante : integrantes)
			desenvolvedores.add(new IntegranteDTO(integrante));

		return desenvolvedores;
	}
}
