package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class AuthView implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = 
			Logger.getLogger(AuthView.class.getName());
	
	private String username;
	
	private String password;
	
	@Inject
	private FacesContext facesContext;
	
	public String login() {
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

		try {
			request.login(username, password);
			return "/index.jsf?faces.redirect=true";
		} catch (ServletException e) {
			FacesUtils.showError("Usuário/senha inválido");
			return "/login.jsf?error=true";
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer o login.");
			return "login.jsf";
		}
			
	}
		
		
	
		
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		try {
			request.logout();
			return "/login?faces-redirect=true&logout=true";
		} catch (ServletException e) {
			FacesUtils.showError("Ocorreu um erro ao fazer o logout.");
			return "/login?faces-redirect=true&error=true";
		}
	}

	/*
	 * Auto-generated
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}
