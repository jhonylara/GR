package br.com.backend.requisitos.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.backend.requisitos.entity.Projeto;
// hibernate + jpa\

@Entity
@NamedQueries({
	@NamedQuery(
			name="CasoDeUso.findAll",
	        query="SELECT c FROM CasoDeUso c INNER JOIN c.projeto p WHERE p.id = :idProjeto"
	),
	@NamedQuery(
			name="CasoDeUso.findById",
			query="SELECT c FROM CasoDeUso c INNER JOIN c.projeto p WHERE p.id = :idProjeto AND c.id = :idCasoDeUso"
	    ),
	@NamedQuery(
			name="CasoDeUso.findByNameInProject", 
			query="SELECT c FROM CasoDeUso c WHERE c.nome = :casoDeUsoNome"
	)
})
public class CasoDeUso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caso_de_uso_id", nullable = false)
	private Integer id;

	@Column(name = "caso_de_uso_nome", nullable = false)
	private String nome;
	
	@Column(name = "caso_de_uso_escopo", nullable = false)
	private String escopo;
	
	@Column(name = "caso_de_uso_nivel", nullable = false)
	private String nivel;

	@Column(name = "caso_de_uso_pre_condicao", nullable = false)
	private String preCondicao;
	
	@Column(name = "caso_de_uso_data_inclusao", nullable = false)
	private Calendar dataInclusao;
	
	@Column(name = "caso_de_uso_data_alteracao", nullable = true)
	private Calendar dataAlteracao;
	
	@ManyToOne()
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;
	
	@ManyToOne()
	@JoinColumn(name = "integrante_id", nullable = false)
	private Integrante integrante;
	
	public CasoDeUso() {}

	public CasoDeUso(
		Integer id,
		String nome,
		String escopo,
		String nivel,
		String interessesInteressados,
		String preCondicao,
		Calendar dataInclusao,
		Calendar dataAlteracao,
		Projeto projeto,
		Integrante integrante
	) {
		super();
		this.id = id;
		this.nome = nome;
		this.escopo = escopo;
		this.nivel = nivel;
		this.preCondicao = preCondicao;
		this.dataInclusao = dataInclusao;
		this.dataAlteracao = dataAlteracao;
		this.projeto = projeto;
		this.integrante = integrante;
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
