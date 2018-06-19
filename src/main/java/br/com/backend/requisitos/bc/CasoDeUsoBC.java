package br.com.backend.requisitos.bc;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.CasoDeUsoDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Projeto;

public class CasoDeUsoBC extends AbstractBusiness<CasoDeUso, Integer> {
	
	@Inject 
	private ProjetoDAO projetoDAO;
	
	@Inject 
	private CasoDeUsoDAO casoDeUsoDAO;
	
	@Transactional
	public void create(CasoDeUso casoDeUso) throws Exception {
		try {
			
			
			Projeto projeto = projetoDAO.find(casoDeUso.getProjeto().getId());
			if(projeto == null) throw new Exception("Sem projeto correspondente");
			CasoDeUso casoDeUsoExistente = (CasoDeUso) casoDeUsoDAO.findByNameInProject(casoDeUso);
			if(casoDeUsoExistente != null) throw new Exception("CasoDeUso já existente");
			
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

