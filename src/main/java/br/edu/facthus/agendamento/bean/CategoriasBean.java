package br.edu.facthus.agendamento.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.facthus.agendamento.entity.Categoria;

@Stateless
public class CategoriasBean {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Categoria> buscaCategorias() {
		return entityManager
				.createQuery("SELECT c FROM Categoria c", Categoria.class)
				.getResultList();
	}
	
	public void salvaCategoria(Categoria categoria) {
		categoria.setAtiva(true);
		entityManager.persist(categoria);
	}
	

}
