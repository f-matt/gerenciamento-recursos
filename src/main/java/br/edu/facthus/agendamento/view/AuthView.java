package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private SecurityContext securityContext;
	
	@Inject
	private ExternalContext externalContext;
	
	@Inject
	private FacesContext facesContext;
	
	public void login() {
		try {
			if (securityContext == null) { 
				FacesUtils.showError("Não foi possí­vel carregar o contexto de autenticação.");
				return;
			}
			
			Credential credential = new UsernamePasswordCredential(username, password);
			AuthenticationStatus status = securityContext.authenticate(getRequestFrom(facesContext),
					getResponseFrom(facesContext), AuthenticationParameters.withParams().credential(credential));
			switch (status) {
	        case SEND_CONTINUE:
	        	facesContext.responseComplete();
	            break;
	        case SEND_FAILURE:
	        	FacesUtils.showError("Usuário/senha inválidos");
	            break;
	        case SUCCESS:
	            externalContext.redirect(externalContext.getRequestContextPath() + "/index.jsf");
	            break;
	        default:
	        	logger.severe("Erro processando autenticação. Status = " + status.name());
	        	FacesUtils.showError("Ocorreu um erro ao processar a autenticação.");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro processando autenticação", e);
			FacesUtils.showError("Ocorreu um erro ao processar a autenticação.");
		}
	}
	
	private static HttpServletResponse getResponseFrom(FacesContext context) {
		return (HttpServletResponse) context.getExternalContext().getResponse();
	}

	private static HttpServletRequest getRequestFrom(FacesContext context) {
		return (HttpServletRequest) context.getExternalContext().getRequest();
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
