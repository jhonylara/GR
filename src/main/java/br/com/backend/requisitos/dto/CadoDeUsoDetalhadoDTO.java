package br.com.backend.requisitos.dto;

import java.util.Calendar;

import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

public class CadoDeUsoDetalhadoDTO {

		private Integer id;

		private String nome;
		
		private String escopo;
		
		private String nivel;
		
		private String preCondicao;
		
		private Calendar dataInclusao;
		
		private Calendar dataAlteracao;
		
		private Projeto projeto;
		
		private Integrante integrante;
		
	
		public CadoDeUsoDetalhadoDTO() {}
		
		public CadoDeUsoDetalhadoDTO(CasoDeUso casoDeUso) {
			this.id = casoDeUso.getId();
			this.nome = casoDeUso.getNome();
			this.escopo = casoDeUso.getEscopo();
			this.nivel = casoDeUso.getNivel();
			this.preCondicao = casoDeUso.getPreCondicao();
			this.dataInclusao = casoDeUso.getDataInclusao();
			this.dataAlteracao =  casoDeUso.getDataAlteracao();
			this.projeto = casoDeUso.getProjeto();
			this.integrante = casoDeUso.getIntegrante();
					
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
		
		public Projeto getProjeto() {
			return projeto;
		}

		public void setProjeto(Projeto projeto) {
			this.projeto = projeto;
		}

		public Integrante getIntegrante() {
			return integrante;
		}

		public void setIntegrante(Integrante integrante) {
			this.integrante = integrante;
		}


}
