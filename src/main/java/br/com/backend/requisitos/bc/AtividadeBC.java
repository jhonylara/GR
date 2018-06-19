package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.AtividadeDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.RequisitoDAO;
import br.com.backend.requisitos.dto.AtividadeDTO;
import br.com.backend.requisitos.dto.AtividadeDetalhadaDTO;
import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Requisito;
import br.com.backend.requisitos.utils.Util;

public class AtividadeBC extends AbstractBusiness<Atividade, Integer> {
	
	@Inject 
	private AtividadeDAO atividadeDAO;
	
	@Inject 
	private RequisitoDAO requisitoDAO;
	
	@Inject
	private IntegranteDAO integranteDAO;
	
	@Transactional
	public void create(Atividade atividade, Integer idRequisito) throws Exception {
		try {
			Requisito requisito = requisitoDAO.find(idRequisito);
			if(requisito == null) throw new Exception("Requisito não encontrado");
			atividade.setDataInclusao(Util.currentDate());

			Integrante criador = integranteDAO.find(atividade.getCriador().getId());
			atividade.setCriador(criador);
			
			Integrante desenvolvedor = integranteDAO.find(atividade.getDesenvolvedores().get(0).getId());
			List<Integrante> desenvolvedores = new ArrayList<Integrante>();
			desenvolvedores.add(desenvolvedor);
			atividade.setDesenvolvedores(desenvolvedores);

			atividadeDAO.create(atividade);
		}
		catch(Exception e) {
			throw e;
		}
	}

	public List<AtividadeDTO> list() {
		try {
			List<AtividadeDTO> atividadeDTOs = new ArrayList<AtividadeDTO>();
			List<Atividade> atividades = atividadeDAO.list();

			for (Atividade atividade : atividades)
				atividadeDTOs.add(new AtividadeDTO(atividade));

			return atividadeDTOs;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public AtividadeDetalhadaDTO find(Integer idRequisito, Integer idAtividade) throws Exception {
		try {
			Requisito requisito = requisitoDAO.find(idRequisito);
			if(requisito == null) throw new Exception("Requisito não encontrado");
			
			Atividade atividade = atividadeDAO.find(idAtividade);
			if(atividade == null) throw new Exception("Atividade não encontrada");
				
			return new AtividadeDetalhadaDTO(atividade);
		} catch (Exception e) {
			throw e;
		}
	};
	
//	private Projeto insertProjetoInRequisito(Requisito requisito) throws Exception {
//		try {
//			Projeto projeto = projetoDAO.find(requisito.getProjeto().getId());
//			if(projeto == null) throw new Exception("Projeto não encontrado");
//
//			List<Requisito> projetoHasRequisitos = projeto.getRequisitos();
//			projetoHasRequisitos.add(requisito);
//			
//			projeto.setRequisitos(projetoHasRequisitos);
//			return projeto;
//		} catch (Exception error) {
//			throw error;
//		}
//	}
//	
//	private Integrante insertIntegranteInRequisito(Requisito requisito) throws Exception {
//		try {
//			Integrante integrante = integranteDAO.find(requisito.getIntegrante().getId());
//			if(integrante == null) throw new Exception("Usuário não encontrado");
//			
//			List<Requisito> integranteHasRequisitos = integrante.getRequisitos();
//			integranteHasRequisitos.add(requisito);
//			
//			integrante.setRequisitos(integranteHasRequisitos);
//			return integrante;
//		} catch (Exception error) {
//			throw error;
//		}
//	}
}
