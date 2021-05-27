package br.edu.facthus.agendamento.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.util.CustomRuntimeException;

@Stateless
public class CategoriasBean {
	
	private static final Logger logger = Logger.getLogger(CategoriasBean.class.getName());
	
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
		try {
			entityManager
					.createNamedQuery("Categoria.findByDescricao", Categoria.class)
					.setParameter("descricao", categoria.getDescricao().toUpperCase())
					.getSingleResult();
			
			throw new CustomRuntimeException("Já existe categoria cadastrada com esta descrição.");
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (NoResultException e) {
			// OK!
		} catch (NonUniqueResultException e) {
			throw new CustomRuntimeException("Mais de uma categoria encontrada com esta descrição.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Não foi possível cadastrar a categoria.");
		}
		
		categoria.setAtiva(true);
		entityManager.persist(categoria);
	}
	
	public void atualizaCategoria(Categoria categoria) {
		entityManager.merge(categoria);
	}

}
