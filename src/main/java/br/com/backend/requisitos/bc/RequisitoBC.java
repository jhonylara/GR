package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.RequisitoDAO;
import br.com.backend.requisitos.dto.RequisitoDTO;
import br.com.backend.requisitos.dto.RequisitoDetalhadoDTO;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;

public class RequisitoBC extends AbstractBusiness<Requisito, Integer> {
	
	@Inject 
	private RequisitoDAO requisitoDAO;
	
	@Inject 
	private ProjetoDAO projetoDAO;
	
	@Inject 
	private IntegranteDAO integranteDAO;
	
	@Transactional
	public void create(Integer idProjeto, Requisito requisito) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Projeto não encontrado");
			
			Integrante integrante = integranteDAO.findById(projeto.getId(), requisito.getIntegrante().getId());
			if(integrante == null) throw new Exception("Integrante não encontrado");

			Calendar date = Calendar.getInstance();
			requisito.setDataInclusao(date);

			requisito.setProjeto(this.insertProjetoInRequisito(projeto, requisito));
			requisito.setIntegrante(this.insertIntegranteInRequisito(integrante, requisito));

			requisitoDAO.create(requisito);
		}
		catch(Exception e) {
			throw e;
		}
	}

	public List<RequisitoDTO> list(Integer idProjeto) {
		try {
			List<RequisitoDTO> requisitosDTOs = new ArrayList<RequisitoDTO>();
			for (Requisito requisito : requisitoDAO.list(idProjeto))
				requisitosDTOs.add(new RequisitoDTO(requisito));

			return requisitosDTOs;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public RequisitoDetalhadoDTO find(Integer idProjeto, Integer idRequisito) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Projeto não encontrado");
			
			Requisito requisito = requisitoDAO.find(idProjeto, idRequisito);
			if(requisito == null) throw new Exception("Requisito não encontrado");

			return new RequisitoDetalhadoDTO(requisito);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Projeto insertProjetoInRequisito(Projeto projeto, Requisito requisito) throws Exception {
		try {
			List<Requisito> projetoHasRequisitos = projeto.getRequisitos();
			projetoHasRequisitos.add(requisito);
			
			projeto.setRequisitos(projetoHasRequisitos);
			return projeto;
		} catch (Exception error) {
			throw error;
		}
	}
	
	private Integrante insertIntegranteInRequisito(Integrante integrante, Requisito requisito) throws Exception {
		try {
			List<Requisito> integranteHasRequisitos = integrante.getRequisitos();
			integranteHasRequisitos.add(requisito);
			
			integrante.setRequisitos(integranteHasRequisitos);
			return integrante;
		} catch (Exception error) {
			throw error;
		}
	}
}

