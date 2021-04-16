package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.facthus.agendamento.bean.CategoriasBean;
import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class CategoriasView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriasBean categoriasBean;
	
	private List<Categoria> categorias;
	
	private Categoria categoria;
	
	public void pesquisa() {
		// TODO: ajustar para pesquisa
		categorias = categoriasBean.buscaCategorias();
	}
		
	public void novaCategoria() {
        categoria = new Categoria();
    }
	
	public void salvaCategoria() {
		categoriasBean.salvaCategoria(categoria);
		FacesUtils.showInfo("Categoria cadastrada com sucesso!");
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
