package br.edu.facthus.agendamento.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {
	
	public static void showInfo(String mensagem) {
		FacesContext
			.getCurrentInstance()
			.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}
	
	public static void showError(String mensagem) {
		FacesContext
			.getCurrentInstance()
			.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}

}
