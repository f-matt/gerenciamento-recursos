package br.edu.facthus.agendamento.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SolicitacoesBean {

	@PersistenceContext
	private EntityManager entityManager;
	
	
}
