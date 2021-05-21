package br.edu.facthus.agendamento.bean;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.entity.Recurso;

@Stateless
public class RecursosBean {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvaRecurso(Recurso recurso) {
		recurso.setAtiva(true);
		entityManager.persist(recurso);
	}
	
	public void atualizaRecurso(Recurso recurso) {
		entityManager.merge(recurso);
	}

	public List<Recurso> buscaRecursos() {
		return entityManager
				.createQuery("SELECT r FROM Recurso r", Recurso.class)
				.getResultList();
	}

}


