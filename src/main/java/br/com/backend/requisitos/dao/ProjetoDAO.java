package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Projeto;

public class ProjetoDAO extends AbstractDAO<Projeto, Integer> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void create(Projeto projeto) {
		try {
			getEntityManager().persist(projeto);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Projeto> list() {
		try {
			return getEntityManager().createNamedQuery("Projeto.findAll", Projeto.class).getResultList(); 
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Projeto find(Integer idProjeto) {
		try {
			List<Projeto> projeto = getEntityManager()
					.createNamedQuery("Projeto.findById", Projeto.class)
					.setParameter("idProjeto", idProjeto)
					.getResultList();
			return projeto.size() > 0 ? projeto.get(0) : null;
		} catch (Exception error) {
			throw error;
		}
	}
	
	public List<Projeto> findByUser(Integer idUsuario) {
		try {
		    List<Projeto> projetos = getEntityManager()
		    		.createNamedQuery("Projeto.findByUser", Projeto.class)
		    		.setParameter("idUsuario", idUsuario)
		    		.getResultList();
			return projetos.size() > 0 ? projetos : null;
		} catch (Exception e) {
			throw e;
		}
	}
}
