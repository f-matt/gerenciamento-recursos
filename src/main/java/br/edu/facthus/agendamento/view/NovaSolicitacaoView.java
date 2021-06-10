package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.facthus.agendamento.bean.CategoriasBean;
import br.edu.facthus.agendamento.bean.RecursosBean;
import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.entity.Recurso;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class NovaSolicitacaoView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriasBean categoriasBean;
	
	@Inject
	private RecursosBean recursosBean;
	
	private Categoria categoria;
	
	private List<Categoria> categorias;
	
	private List<Recurso> recursos;
	
	@PostConstruct
	public void init() {
		categorias = categoriasBean.buscaCategorias();
	}
		
	public void pesquisaRecursos() {
		if (categoria == null) {
			FacesUtils.showError("É necessário selecionar uma categoria.");
			return;
		}
		
		recursos = recursosBean.buscaPorCategoria(categoria);
		if (recursos.isEmpty())
			FacesUtils.showError("Nenhum recurso encontrado na categoria selecionada.");
	}
	
	public void registraSolicitacao() {
		FacesUtils.showInfo("Em construção...");
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

}
