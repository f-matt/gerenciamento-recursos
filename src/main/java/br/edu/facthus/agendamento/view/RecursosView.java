package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.edu.facthus.agendamento.bean.RecursosBean;
import br.edu.facthus.agendamento.entity.Recurso;

@Named
@ViewScoped
public class RecursosView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = 
			Logger.getLogger(RecursosView.class.getName());
	
	@Inject
	private RecursosBean recursosBean;
	
	private List<Recurso> recursos;
	
	private Recurso recurso;
	
	private Integer codigoPesquisa;
	
	private String descricaoPesquisa;
	
	public void pesquisaPorCodigo() {
//		try {
//			categorias = new ArrayList<>();
//			if (codigoPesquisa == null) {
//				FacesUtils.showError("É necessário informar o código.");
//				return;
//			}
//			
//			Categoria c = recursosBean
//					.buscaPorCodigo(codigoPesquisa);
//			if (c == null) {
//				FacesUtils
//					.showError("Nenhuma categoria encontrada com o código informado.");
//				return;
//			}
//			
//			categorias.add(c);
//		} catch (Exception e) {
//			logger.log(Level.SEVERE, e.getMessage(), e);
//			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
//		}
	}
	
	public void pesquisaPorDescricao() {
//		try {
//			categorias = new ArrayList<>();
//			if (descricaoPesquisa == null || descricaoPesquisa.isBlank()) {
//				FacesUtils.showError("É necessário informar a descrição.");
//				return;
//			}
//			
//			categorias = recursosBean
//					.buscaPorDescricao(descricaoPesquisa);
//			if (categorias.isEmpty())
//				FacesUtils
//					.showError("Nenhuma categoria encontrada com a descrição informada.");
//		} catch (Exception e) {
//			logger.log(Level.SEVERE, e.getMessage(), e);
//			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
//		}
	}
		
	public void novoRecurso() {
        recurso = new Recurso();
    }
	
	public void salvaRecurso() {
//		try {
//			if (categoria.getId() == null) {
//				recursosBean.salvaCategoria(categoria);
//				categoria = new Categoria();
//				FacesUtils.showInfo("Categoria cadastrada com sucesso!");
//			} else {
//				recursosBean.atualizaCategoria(categoria);
//				FacesUtils.showInfo("Categoria atualizada com sucesso!");
//			}
//		} catch (Exception e) {
//			FacesUtils.showError("Ocorreu um erro ao cadastrar a categoria.");
//		}
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

}
