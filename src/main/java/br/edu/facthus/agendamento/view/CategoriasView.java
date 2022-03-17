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

import br.edu.facthus.agendamento.bean.CategoriasBean;
import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.util.CustomRuntimeException;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class CategoriasView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = 
			Logger.getLogger(CategoriasView.class.getName());
	
	@Inject
	private CategoriasBean categoriasBean;
	
	private List<Categoria> categorias;
	
	private Categoria categoria;
	
	private Integer codigoPesquisa;
	
	private String descricaoPesquisa;
	
	public void pesquisaPorCodigo() {
		try {
			categorias = new ArrayList<>();
			if (codigoPesquisa == null) {
				FacesUtils.showError("É necessário informar o código.");
				return;
			}
			
			Categoria c = categoriasBean
					.buscaPorCodigo(codigoPesquisa);
			if (c == null) {
				FacesUtils
					.showError("Nenhuma categoria encontrada com o código informado.");
				return;
			}
			
			categorias.add(c);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}
	
	public void pesquisaPorDescricao() {
		try {
			categorias = new ArrayList<>();
			if (descricaoPesquisa == null || descricaoPesquisa.isEmpty()) {
				FacesUtils.showError("É necessário informar a descrição.");
				return;
			}
			
			categorias = categoriasBean
					.buscaPorDescricao(descricaoPesquisa);
			if (categorias.isEmpty())
				FacesUtils
					.showError("Nenhuma categoria encontrada com a descrição informada.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}
		
	public void novaCategoria() {
        categoria = new Categoria();
    }
	
	public void salvaCategoria() {
		if (categoria == null) {
			FacesUtils.showError("É necessário selecionar uma categoria.");
			return;
		}
		
		if (categoria.getDescricao() == null || categoria.getDescricao().isEmpty()) {
			FacesUtils.showError("É necessário informar a descrição.");
			return;
		}
		
		try {
			if (categoria.getId() == null) {
				categoriasBean.salvaCategoria(categoria);
				categoria = new Categoria();
				FacesUtils.showInfo("Categoria cadastrada com sucesso!");
			} else {
				categoriasBean.atualizaCategoria(categoria);
				FacesUtils.showInfo("Categoria atualizada com sucesso!");
			}
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
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

	public Integer getCodigoPesquisa() {
		return codigoPesquisa;
	}

	public void setCodigoPesquisa(Integer codigoPesquisa) {
		this.codigoPesquisa = codigoPesquisa;
	}

	public String getDescricaoPesquisa() {
		return descricaoPesquisa;
	}

	public void setDescricaoPesquisa(String descricaoPesquisa) {
		this.descricaoPesquisa = descricaoPesquisa;
	}

}
