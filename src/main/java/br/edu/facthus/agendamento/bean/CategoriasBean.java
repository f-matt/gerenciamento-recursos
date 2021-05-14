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
	
	public Categoria buscaPorCodigo(Integer codigo) {
		return entityManager.find(Categoria.class, codigo);
	}
	
	public List<Categoria> buscaPorDescricao(String descricao) {
		return entityManager
				.createNamedQuery("Categoria.findByDescricao", 
						Categoria.class)
				.setParameter("descricao", String.format("%%%s%%", 
						descricao.toUpperCase()))
				.getResultList();
	}

	public List<Categoria> buscaCategorias() {
		return entityManager
				.createQuery("SELECT c FROM Categoria c", Categoria.class)
				.getResultList();
	}
	
	public void salvaCategoria(Categoria categoria) {
		categoria.setAtiva(true);
		entityManager.persist(categoria);
	}
	
	public void atualizaCategoria(Categoria categoria) {
		entityManager.merge(categoria);
	}

}
