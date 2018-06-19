package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.UsuarioDAO;
import br.com.backend.requisitos.dto.IntegranteDTO;
import br.com.backend.requisitos.dto.IntegranteDetalhadoDTO;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Usuario;
import br.com.backend.requisitos.utils.Util;

public class IntegranteBC extends AbstractBusiness<Integrante, Integer> {
	
	@Inject 
	private IntegranteDAO integranteDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;
	
	@Transactional
	public void create(Integer idProjeto, Integrante integrante) throws Exception {
		try {
			Usuario usuario = usuarioDAO.find(integrante.getUsuario().getId());
			if(usuario == null) throw new Exception("Usuário não encontrado");

			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Projeto não encontrado");
			projeto.setDataInclusao(Util.currentDate());

			this.Integrante_has_Projeto(projeto, integrante);
		}
		catch(Exception e) {
			throw e;
		}
	}

	public List<IntegranteDTO> list(Integer idProjeto) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Projeto não encontrado");

			List<Integrante> integrantes = integranteDAO.list(idProjeto);

			List<IntegranteDTO> integranteDTOs = new ArrayList<IntegranteDTO>();
			for (Integrante integrante : integrantes)
				integranteDTOs.add(new IntegranteDTO(integrante));

			return integranteDTOs;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public IntegranteDetalhadoDTO findById(Integer idProjeto, Integer idIntegrante) throws Exception {
		try {
			Projeto projeto = projetoDAO.find(idProjeto);
			if(projeto == null) throw new Exception("Projeto não encontrado");

			Integrante integrante = integranteDAO.findById(idProjeto, idIntegrante);
			if(integrante == null) throw new Exception("Integrante não encontrado");

			return new IntegranteDetalhadoDTO(integrante);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void Integrante_has_Projeto(Projeto projeto, Integrante integrante) throws Exception {
		try {
			Integrante integ = Util.insertProjetoToIntegrante(projeto, integrante);
			if(integ == null) throw new Exception("Não foi possivel adicionar integrante.");
			
			integ.setDataInclusao(Util.currentDate());
			Integrante integranteWithProjeto = integranteDAO.persist(integ);
			
			if(integranteWithProjeto == null) throw new Exception("Erro ao adicionar novo integrante");
			Projeto proj = Util.insetIntegranteToProjeto(projeto, integranteWithProjeto);

			projetoDAO.create(proj);
		} catch (Exception error) {
			error.printStackTrace();
			throw error;
		}
	}
}
