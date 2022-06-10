package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.edu.facthus.agendamento.bean.CategoriasBean;
import br.edu.facthus.agendamento.bean.RecursosBean;
import br.edu.facthus.agendamento.entity.Categoria;
import br.edu.facthus.agendamento.entity.Recurso;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class RecursosView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = 
			Logger.getLogger(RecursosView.class.getName());
	
	@Inject
	private RecursosBean recursosBean;
	
	@Inject
	private CategoriasBean categoriasBean;
	
	private List<Recurso> recursos;
	
	private Recurso recurso;
	
	private Integer codigoPesquisa;
	
	private String descricaoPesquisa;
	
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		categorias = categoriasBean.buscaCategorias();
	}
	
	public void pesquisaPorCodigo() {
		try {
			recursos = new ArrayList<>();
			if (codigoPesquisa == null) {
				FacesUtils.showError("É necessário informar o código.");
				return;
			}
			
			Recurso r = recursosBean.buscaPorCodigo(codigoPesquisa);
			if (r == null) {
				FacesUtils
					.showError("Nenhum recurso encontrado com o código informado.");
				return;
			}
			
			recursos.add(r);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}
	
	public void pesquisaPorDescricao() {
		try {
			recursos = new ArrayList<>();
			if (descricaoPesquisa == null || descricaoPesquisa.isBlank()) {
				FacesUtils.showError("É necessário informar a descrição.");
				return;
			}
			
			recursos = recursosBean.buscaPorDescricao(descricaoPesquisa);
			if (categorias.isEmpty())
				FacesUtils
					.showError("Nenhum recurso encontrado com a descrição informada.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}
		
	public void novoRecurso() {
        recurso = new Recurso();
    }
	
	public void salvaRecurso() {
		try {
			if (recurso.getId() == null) {
				recursosBean.salvaRecurso(recurso);
				recurso = new Recurso();
				FacesUtils.showInfo("Recurso cadastrado com sucesso!");
				PrimeFaces.current().executeScript("PF('recursosDialog').hide()");
			} else {
				recursosBean.atualizaRecurso(recurso);
				FacesUtils.showInfo("Recurso atualizado com sucesso!");
			}
		} catch (Exception e) {
			FacesUtils.showError("Ocorreu um erro ao cadastrar a categoria.");
		}
	}
	
	public void openDlgEditar(Recurso recurso) {
		this.recurso = recurso;
		PrimeFaces.current().executeScript("PF('recursosDialog').show()");
		
	}

	/*
	 * Auto-generated
	 */
	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
