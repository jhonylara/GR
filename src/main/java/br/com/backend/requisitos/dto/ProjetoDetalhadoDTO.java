package br.com.backend.requisitos.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;

public class ProjetoDetalhadoDTO {

	private ProjetoDTO projeto;
	
	private List<IntegranteDTO> integrantes; 
	
	private List<RequisitoDTO> requisito;
	
	private List<CasoDeUsoDTO> casosDeUso;
	
	public ProjetoDetalhadoDTO() {}
	
	public ProjetoDetalhadoDTO(Projeto projeto) {
		this.projeto = new ProjetoDTO(projeto);
		this.integrantes = this.listaIntegrantes(projeto.getIntegrantes());
		this.requisito = this.listaRequisitos(projeto.getRequisitos());
		this.casosDeUso = this.getCasoDeUsoDTO(projeto.getCasosDeUso());
	}

	public ProjetoDTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDTO projeto) {
		this.projeto = projeto;
	}

	public List<IntegranteDTO> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<IntegranteDTO> integrantes) {
		this.integrantes = integrantes;
	}

	public List<RequisitoDTO> getRequisito() {
		return requisito;
	}

	public void setRequisito(List<RequisitoDTO> requisito) {
		this.requisito = requisito;
	}

	public List<CasoDeUsoDTO> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUsoDTO> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}
	
	private List<IntegranteDTO> listaIntegrantes(List<Integrante> integrantes) {
		if (integrantes.isEmpty()) return null;
		
		List<IntegranteDTO> integrantesDTO = new ArrayList<IntegranteDTO>();
		for (Integrante integrante : integrantes) 	
			integrantesDTO.add(new IntegranteDTO(integrante));
		
		return integrantesDTO;
	}
	
	private List<RequisitoDTO> listaRequisitos(List<Requisito> requisitos) {
		if (requisitos.isEmpty()) return null;
		int iteratorLength = requisitos.size() > 3 ? 3 : requisitos.size();

		List<RequisitoDTO> requisitoDTO = new ArrayList<RequisitoDTO>();
		for (int iterator = 0; iterator < iteratorLength; iterator++)
			requisitoDTO.add(new RequisitoDTO(requisitos.get(iterator)));

		return requisitoDTO;
	}
	
	private List<CasoDeUsoDTO> getCasoDeUsoDTO(List<CasoDeUso> casosDeUso) {
		if (casosDeUso.isEmpty()) return null;
		int iteratorLength = casosDeUso.size() > 3 ? 3 : casosDeUso.size();

		List<CasoDeUsoDTO> casoDeUsoDTO = new ArrayList<CasoDeUsoDTO>();
		for (int iterator = 0; iterator < iteratorLength; iterator++)
			casoDeUsoDTO.add(new CasoDeUsoDTO(casosDeUso.get(iterator)));

		return casoDeUsoDTO;
	}
}
