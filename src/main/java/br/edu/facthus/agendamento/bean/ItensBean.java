package br.edu.facthus.agendamento.bean;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.facthus.agendamento.entity.Item;

@Stateless

public class ItensBean {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Item buscaPorCodigo(Integer codigo) {
		return entityManager.find(Item.class, codigo);
	}

	public List<Item> buscaPorDescricao(String descricao) {
		return entityManager
				.createNamedQuery("Item.findByDescricao", 
						Item.class)
				.setParameter("descricao", String.format("%%%s%%", 
						descricao.toUpperCase()))
				.getResultList();
	}
	
	public List<Item> buscaItem() {
		return entityManager
				.createQuery("SELECT i FROM Item i", Item.class)
				.getResultList();
	}
	
	public void salvaItem(Item item) {
		item.setAtiva(true);
		item.setDisponivel(true);
		entityManager.persist(item);
	}
	
	public void atualizaItem(Item item) {
		entityManager.merge(item);
	}
}
