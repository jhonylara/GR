package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Atividade;

public class AtividadeDAO extends AbstractDAO<Atividade, Integer> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void create(Atividade atividade) {
		try {
			getEntityManager().persist(atividade);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Atividade> list() {
		try {
			return getEntityManager().createNamedQuery("Atividade.findAll", Atividade.class).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

}
