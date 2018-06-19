package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Integrante;

public class IntegranteDAO extends AbstractDAO<Integrante, Integer> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void create(Integrante integrante) {
		try {
			getEntityManager().persist(integrante);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Integrante> list(Integer idProjeto) {
		try {
			return getEntityManager()
					.createNamedQuery("Integrante.findAll", Integrante.class)
					.setParameter("idProjeto", idProjeto)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Integrante findById(Integer idProjeto, Integer idIntegrante) {
		try {
			List<Integrante> integrantes = getEntityManager()
					.createNamedQuery("Integrante.findById", Integrante.class)
					.setParameter("idProjeto", idProjeto)
					.setParameter("idIntegrante", idIntegrante)
					.getResultList();

			return integrantes.size() > 0 ? integrantes.get(0) : null;
		} catch (Exception error) {
			throw error;
		}
	}
}
