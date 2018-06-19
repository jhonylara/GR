package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.UsuarioDAO;
import br.com.backend.requisitos.dto.ProjetoDTO;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Usuario;

public class UsuarioBC extends AbstractBusiness<Usuario, Integer> {
	
	@Inject 
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;
	
	@Transactional
	public void create(Usuario usuario) throws Exception {
		try {
			Usuario novoUsuario = usuarioDAO.findByEmail(usuario.getEmail());
			if(novoUsuario != null) throw new Exception("Email já cadastrado"); 

			usuarioDAO.create(usuario);
		}
		catch(Exception e) {
			throw e;
		}
	}

	public List<Usuario> list() {
		try {
			return usuarioDAO.list();
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public List<ProjetoDTO> findByProjectsForUser(Integer idUsuario) throws Exception {
		try {
			Usuario usuario = usuarioDAO.find(idUsuario);
			if(usuario == null) throw new Exception("Usuário não encontrado");

			List<Projeto> projetos = projetoDAO.findByUser(idUsuario);
			if(projetos == null) throw new Exception("Projetos não encontrados");

			List<ProjetoDTO> projetoDTOs = new ArrayList<ProjetoDTO>();
			for (Projeto projeto : projetos)
				projetoDTOs.add(new ProjetoDTO(projeto));

			return projetoDTOs;
		} catch (Exception error) {
			error.printStackTrace();
			throw error;
		}
	}
}
