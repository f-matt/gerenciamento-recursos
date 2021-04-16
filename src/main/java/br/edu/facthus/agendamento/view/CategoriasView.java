package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.facthus.agendamento.bean.CategoriasBean;
import br.edu.facthus.agendamento.entity.Categoria;

@Named
@ViewScoped
public class CategoriasView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriasBean categoriasBean;
	
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		categorias = categoriasBean.buscaCategorias();
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	

}
