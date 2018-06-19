package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dto.ProjetoDTO;
import br.com.backend.requisitos.dto.ProjetoDetalhadoDTO;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.enums.PerfilIntegranteProjeto;
import br.com.backend.requisitos.utils.Util;

public class ProjetoBC extends AbstractBusiness<Projeto, Integer> {
	
	@Inject
	private ProjetoDAO projetoDAO;

	@Inject
	private IntegranteBC integranteBC;
	
	@Transactional
	public void create(Projeto projeto) throws Exception {
		try {
			projeto.setDataInclusao(Util.currentDate());
			projeto.getIntegrantes().get(0).setPerfilIntegranteProjeto(PerfilIntegranteProjeto.GERENTE);
			
			this.Integrante_has_Projeto(projeto);
		}
		catch(Exception e) {
			throw e;
		}
	}

	public List<ProjetoDTO> list() {
		try {
			List<ProjetoDTO> projetoDTOs = new ArrayList<ProjetoDTO>();
			
			for (Projeto projeto : projetoDAO.list())
				projetoDTOs.add(new ProjetoDTO(projeto));

			return projetoDTOs; 
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public ProjetoDetalhadoDTO listDetailed(Integer idProjeto) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Projeto não encontrado");

			return new ProjetoDetalhadoDTO(projeto);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void Integrante_has_Projeto(Projeto projeto) throws Exception {
		try {
			Integrante integrante = Util.insertProjetoToIntegrante(projeto, projeto.getIntegrantes().get(0));
			if(integrante == null) throw new Exception("Error ao criar novo projeto");

			integrante.setDataInclusao(Util.currentDate());
			Integrante integ = integranteBC.persist(integrante);
			if(integ == null) throw new Exception("Error ao criar novo projeto");

			System.out.println(projeto.getIntegrantes().size());
			projetoDAO.create(projeto);
		} catch (Exception error) {
			error.printStackTrace();
			throw error;
		}
	}
}
