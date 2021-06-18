package br.edu.facthus.agendamento.bean;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.entity.Item;
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
	
	public Recurso buscaPorCodigo(Integer id) {
		return entityManager.find(Recurso.class, id);
	}
	
	public List<Recurso> buscaPorDescricao(String descricao) {
		return entityManager
				.createNamedQuery("Recurso.findByDescricao", Recurso.class)
				.setParameter("descricao", 
						String.format("%%%s%%", descricao.toUpperCase()))
				.getResultList();
	}
	
	public List<Recurso> buscaPorCategoria(Categoria categoria) {
		List<Recurso> recursos = entityManager
				.createNamedQuery("Recurso.findByCategoria", Recurso.class)
				.setParameter("categoria", categoria)
				.getResultList();
		
		for (Recurso recurso : recursos) {
			recurso.setItensDisponiveis(entityManager
					.createNamedQuery("Item.findDisponiveisByRecurso", Item.class)
					.setParameter("recurso", recurso)
					.getResultList()
					.size());
		}
		
		return recursos;
	}

}
