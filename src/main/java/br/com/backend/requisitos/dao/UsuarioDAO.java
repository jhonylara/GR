package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario, Integer> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public Usuario findByEmail(String email) {
		try {
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		    CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		    Path<Object> SQL = query.from(Usuario.class).get("email");
		    query.where(builder.equal(SQL, email));

		    List<Usuario> usuarios = getEntityManager().createQuery(query).getResultList();
			return usuarios.size() > 0 ? usuarios.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}

	public void create(Usuario usuario) {
		try {
			getEntityManager().persist(usuario);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Usuario> list() {
		try {
			CriteriaQuery<Usuario> query = getEntityManager().getCriteriaBuilder().createQuery(Usuario.class);
			query.select(query.from(Usuario.class));

			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
}
