package br.com.backend.requisitos.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
    @NamedQuery(
		name="Projeto.findAll",
        query="SELECT p FROM Projeto p"
    ),
    @NamedQuery(
    	name="Projeto.findById",
        query="SELECT p FROM Projeto p WHERE p.id = :idProjeto"
    ),
    @NamedQuery(
    	name="Projeto.findByName",
    	query="SELECT p FROM Projeto p WHERE p.nome = :nomeProjeto"
    ),
    @NamedQuery(
    	name="Projeto.findByUser",
    	query="SELECT p FROM Projeto p INNER JOIN p.integrantes i INNER JOIN i.usuario u WHERE u.id = :idUsuario"
    )
})
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projeto_id", nullable = false)
	private Integer id;

	@Column(name = "projeto_nome", nullable = false)
	private String nome;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "projeto_data_inicio", nullable = false)
	private Calendar dataInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "projeto_data_fim", nullable = false)
	private Calendar dataFim;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "projeto_data_inclusao", nullable = false)
	private Calendar dataInclusao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "projeto_data_alteracao", nullable = true)
	private Calendar dataAlteracao;

	@OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
	@Column(name = "requisitos", nullable = true)
	private List<Requisito> requisitos;

	@JsonIgnore
	@OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
	@Column(name = "casosDeUso", nullable = true)
	private List<CasoDeUso> casosDeUso;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
        name = "Integrante_has_Projeto",
        joinColumns = { @JoinColumn(name = "projeto_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "integrante_id") }
    )
	@Column(name = "integrantes", nullable = false)
	private List<Integrante> integrantes;
	
	public Projeto(Projeto projeto) {
		super();
		this.id = projeto.id;
		this.nome = projeto.nome;
		this.dataInicio = projeto.dataInicio;
		this.dataFim = projeto.dataFim;
		this.dataInclusao = projeto.dataInclusao;
		this.dataAlteracao = projeto.dataAlteracao;
		this.requisitos = projeto.requisitos;
		this.casosDeUso = projeto.casosDeUso;
		this.integrantes = projeto.integrantes;
	}
	
	public Projeto() {}

	public Projeto(
		Integer id,
		String nome,
		Calendar dataInicio,
		Calendar dataFim,
		Calendar dataInclusao,
		Calendar dataAlteracao,
		List<Requisito> requisitos,
		List<CasoDeUso> casosDeUso,
		List<Integrante> integrantes
	) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.dataInclusao = dataInclusao;
		this.dataAlteracao = dataAlteracao;
		this.requisitos = requisitos;
		this.casosDeUso = casosDeUso;
		this.integrantes = integrantes;
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

	public List<Requisito> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}

	public List<CasoDeUso> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUso> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}

	public List<Integrante> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Integrante> integrantes) {
		this.integrantes = integrantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casosDeUso == null) ? 0 : casosDeUso.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((integrantes == null) ? 0 : integrantes.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((requisitos == null) ? 0 : requisitos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (casosDeUso == null) {
			if (other.casosDeUso != null)
				return false;
		} else if (!casosDeUso.equals(other.casosDeUso))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (integrantes == null) {
			if (other.integrantes != null)
				return false;
		} else if (!integrantes.equals(other.integrantes))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (requisitos == null) {
			if (other.requisitos != null)
				return false;
		} else if (!requisitos.equals(other.requisitos))
			return false;
		return true;
	}
}
