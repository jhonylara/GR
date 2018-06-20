package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.CasoDeUso;


//pega os metodos genericos da classe abstract passa o nome da class, tipo do ID
public class CasoDeUsoDAO extends AbstractDAO<CasoDeUso, Integer>{
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public void create(CasoDeUso casoDeUso) {
		try {
			getEntityManager().persist(casoDeUso);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<CasoDeUso> list() {
		try {
			CriteriaQuery<CasoDeUso> query = getEntityManager().getCriteriaBuilder().createQuery(CasoDeUso.class);
			query.select(query.from(CasoDeUso.class));

			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public CasoDeUso findByNameInProject(Integer idProjeto, String nomeCasoDeUso) {
		try {
			System.out.println(idProjeto);
			System.out.println(nomeCasoDeUso);
			List<CasoDeUso> totalCasosDeUso = getEntityManager()
					.createNamedQuery("CasoDeUso.findByNameInProject", CasoDeUso.class)
					.setParameter("casoDeUsoNome", nomeCasoDeUso)
					//.setParameter("idProjeto", idProjeto)
					.getResultList();
			
		    //query.where(builder.equal(SQL, idUsuario));

		    //List<Projeto> projetos = getEntityManager().createQuery(query).getResultList();
			//return projetos.size() > 0 ? projetos : null;
			
			return totalCasosDeUso.size() > 0 ? totalCasosDeUso.get(0) : null;
		} catch (Exception error) {
			throw error;
		}
	}

	
}
