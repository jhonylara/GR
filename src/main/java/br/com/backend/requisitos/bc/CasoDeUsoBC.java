package br.com.backend.requisitos.bc;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.CasoDeUsoDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

public class CasoDeUsoBC extends AbstractBusiness<CasoDeUso, Integer> {
	
	@Inject 
	private ProjetoDAO projetoDAO;
	
	@Inject 
	private CasoDeUsoDAO casoDeUsoDAO;
	
	@Inject 
	private IntegranteDAO integranteDAO;
	
	@Transactional
	public void create(Integer idProjeto, CasoDeUso casoDeUso) throws Exception {
		try {
					
			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Sem projeto correspondente");
			
			casoDeUso.setProjeto(projeto);
			
			System.out.println(projeto.getId());
			
			Integrante integrante = integranteDAO.findById(idProjeto, casoDeUso.getIntegrante().getId());
			if(integrante == null) throw new Exception("Integrante não encontrado");
			
			casoDeUso.setIntegrante(integrante);
			
			System.out.println(casoDeUso.getProjeto().getId());
			System.out.println("Jhony");
			
			CasoDeUso casoDeUsoExistente = casoDeUsoDAO.findByNameInProject(idProjeto, casoDeUso.getNome());
			if(casoDeUsoExistente != null) throw new Exception("CasoDeUso já existente");
			
			System.out.println("Saiu");
			
			
			
			Calendar date = Calendar.getInstance();
			casoDeUso.setDataInclusao(date);
		
			
			casoDeUsoDAO.create(casoDeUso);
			
			//integrante.setUsuario(usuarioIntegrante);
			
			//integranteDAO.create(integrante);
		}
		catch(Exception e) {
			throw e;
		}	
	}
		public List<CasoDeUso> list() {
			try {
				return casoDeUsoDAO.list();
			}
			catch(Exception e) {
				throw e;
			}
		}		
	}

