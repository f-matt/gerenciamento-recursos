package br.edu.facthus.agendamento.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import br.edu.facthus.agendamento.entity.Usuario;
import br.edu.facthus.agendamento.util.CustomRuntimeException;

@Stateless
public class UsuariosBean {
	private static final Logger logger = Logger.getLogger(UsuariosBean.class.getName());
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Usuario buscaPorCodigo(Integer codigo) {
		return entityManager.find(Usuario.class, codigo);
	}
	
	public List<Usuario> buscaPorNome(String nome) {
		return entityManager
				.createNamedQuery("Usuario.findByNome", 
						Usuario.class)
				.setParameter("nome", String.format("%%%s%%", 
						nome.toUpperCase()))
				.getResultList();
	}

	public List<Usuario> buscaUsuarios() {
		return entityManager
				.createQuery("SELECT c FROM Usuario c", Usuario.class)
				.getResultList();
	}
	
	public void salvaUsuario(Usuario usuario) {
		try {
			entityManager
					.createNamedQuery("Usuario.findByNome", Usuario.class)
					.setParameter("nome", usuario.getNome().toUpperCase())
					.getSingleResult();
			
			throw new CustomRuntimeException("Já existe usuario cadastrada com este nome.");
		} catch (CustomRuntimeException e) {
			throw e;
		} catch (NoResultException e) {
			// OK!
		} catch (NonUniqueResultException e) {
			throw new CustomRuntimeException("Mais de um usuário encontrado com este nome.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new CustomRuntimeException("Não foi possível cadastrar a usuario.");
		}
		
		usuario.setAtivo(true);
		entityManager.persist(usuario);
	}
	
	public void atualizaUsuario(Usuario usuario) {
		entityManager.merge(usuario);
	}
}
