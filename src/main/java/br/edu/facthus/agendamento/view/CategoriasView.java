package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

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
		try {
			if (categoria.getId() == null) {
				categoriasBean.salvaCategoria(categoria);
				categoria = new Categoria();
				FacesUtils.showInfo("Categoria cadastrada com sucesso!");
			} else {
				categoriasBean.atualizaCategoria(categoria);
				FacesUtils.showInfo("Categoria atualizada com sucesso!");
			}
		} catch (Exception e) {
			FacesUtils.showError("Ocorreu um erro ao cadastrar a categoria.");
		}
	}
	
	public void openDlgEditar(Categoria categoria) {
		this.categoria = categoria;
		PrimeFaces.current().executeScript("PF('categoriasDialog').show()");
		
	}

	
	/*
	 * Auto-generated
	 */
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
