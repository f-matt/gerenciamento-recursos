package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.edu.facthus.agendamento.bean.UsuariosBean;
import br.edu.facthus.agendamento.entity.Perfil;
import br.edu.facthus.agendamento.entity.Usuario;
import br.edu.facthus.agendamento.util.CustomRuntimeException;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class UsuariosView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = 
			Logger.getLogger(UsuariosView.class.getName());
	
	@Inject
	private UsuariosBean usuariosBean;
	
	private List<Usuario> usuarios;
	
	private Usuario usuario;
	
	private Integer codigoPesquisa;
	
	private String nomePesquisa;
	
	private List<Perfil> perfis;
	
	private String password;
	
	@PostConstruct
	public void init() {
		try {
			perfis = usuariosBean.buscaPerfis();
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Não foi possível buscar os perfis.");
		}
	}
		
	public void pesquisaPorCodigo() {
		try {
			usuarios = new ArrayList<>();
			if (codigoPesquisa == null) {
				FacesUtils.showError("É necessário informar o código.");
				return;
			}
			
			Usuario u = usuariosBean
					.buscaPorCodigo(codigoPesquisa);
			if (u == null) {
				FacesUtils
					.showError("Nenhum usuário encontrado com o código informado.");
				return;
			}
			
			usuarios.add(u);
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
		if (password == null || password.isBlank()) {
			FacesUtils.showError("É necessário informar a senha.");
			return;
		}
		
		try {
			// Criptografa a senha do usuário
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			usuario.setPassword(encoded);
			
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

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}