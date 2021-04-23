package br.edu.facthus.agendamento.bean;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import br.edu.facthus.agendamento.entity.Recurso;

@Stateless

public class RecursosBean {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public void salvaRecursos(Recurso recursos) {
		recursos.setAtiva(true);
		entityManager.persist(recursos);
	}

}


