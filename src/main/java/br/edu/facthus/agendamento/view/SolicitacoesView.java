package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.facthus.agendamento.entity.Solicitacao;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class SolicitacoesView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoPesquisa;
	
	private List<Solicitacao> solicitacoes;

	public void pesquisaPorCodigo() {
		FacesUtils.showInfo("Em construção...");
	}
	
	public void visualiza(Solicitacao solicitacao) {
		FacesUtils.showInfo("Em construção...");
	}
	
	public Integer getCodigoPesquisa() {
		return codigoPesquisa;
	}

	public void setCodigoPesquisa(Integer codigoPesquisa) {
		this.codigoPesquisa = codigoPesquisa;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

}
