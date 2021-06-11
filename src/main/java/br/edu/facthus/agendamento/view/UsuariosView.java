package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.edu.facthus.agendamento.bean.UsuariosBean;
import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.entity.Usuario;
import br.edu.facthus.agendamento.util.CustomRuntimeException;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class UsuariosView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = 
			Logger.getLogger(CategoriasView.class.getName());
	
	@Inject
	private UsuariosBean usuariosBean;
	
	private List<Usuario> usuarios;
	
	private Usuario usuario;
	
	private Integer codigoPesquisa;
	
	private String nomePesquisa;
	
	public void pesquisaPorCodigo() {
		try {
			usuarios = new ArrayList<>();
			if (codigoPesquisa == null) {
				FacesUtils.showError("É necessário informar o código.");
				return;
			}
			
			Categoria c = usuariosBean
					.buscaPorCodigo(codigoPesquisa);
			if (c == null) {
				FacesUtils
					.showError("Nenhum usuário encontrado com o código informado.");
				return;
			}
			
			usuarios.add(c);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}
	
	public void pesquisaPorNome() {
		try {
			usuarios = new ArrayList<>();
			if (nomePesquisa == null || nomePesquisa.isBlank()) {
				FacesUtils.showError("É necessário informar o nome.");
				return;
			}
			
			usuarios = usuariosBean
					.buscaPorNome(nomePesquisa);
			if (usuarios.isEmpty())
				FacesUtils
					.showError("Nenhum usuário encontrado com o nome informado.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}

	public void novoUsuario() {
		usuario = new Usuario();
	}
	
	public void salvaUsuario() {
		try {
			if (usuario.getId() == null) {
				usuariosBean.salvaUsuario(usuario);
				usuario = new Usuario();
				FacesUtils.showInfo("Usuário cadastrado com sucesso!");
			} else {
				usuariosBean.atualizaUsuario(usuario);
				FacesUtils.showInfo("Usuário atualizado com sucesso!");
			}
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao cadastrar o usuário.");
		}
	}
	
	public void openDlgEditar(Usuario usuario) {
		this.usuario = usuario;
		PrimeFaces.current().executeScript("PF('usuariosDialog').show()");
		
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getCodigoPesquisa() {
		return codigoPesquisa;
	}

	public void setCodigoPesquisa(Integer codigoPesquisa) {
		this.codigoPesquisa = codigoPesquisa;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}
	
}