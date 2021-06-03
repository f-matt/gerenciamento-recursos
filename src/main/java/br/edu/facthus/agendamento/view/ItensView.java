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

import br.edu.facthus.agendamento.bean.ItensBean;
import br.edu.facthus.agendamento.bean.RecursosBean;
import br.edu.facthus.agendamento.entity.Item;
import br.edu.facthus.agendamento.entity.Recurso;
import br.edu.facthus.agendamento.util.FacesUtils;

@Named
@ViewScoped
public class ItensView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = 
			Logger.getLogger(ItensView.class.getName());
	
	@Inject
	private ItensBean itensBean;
	
	@Inject
	private RecursosBean recursosBean;
	
	private List<Item> itens;
	
	private Item item;
	
	private List<Recurso> recursos;
	
	private Integer codigoPesquisa;

	private String descricaoPesquisa;
	
	public void pesquisaPorCodigo() {
		try {
			itens = new ArrayList<>();
			if (codigoPesquisa == null) {
				FacesUtils.showError("É necessário informar o código.");
				return;
			}

			Item i = itensBean
					.buscaPorCodigo(codigoPesquisa);
			if (i == null) {
				FacesUtils
					.showError("Nenhum item encontrado com o código informado.");
				return;
			}

			itens.add(i);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}

	public void pesquisaPorDescricao() {
		try {
			itens = new ArrayList<>();
			if (descricaoPesquisa == null || descricaoPesquisa.isBlank()) {
				FacesUtils.showError("É necessário informar a descrição.");
				return;
			}

			itens = itensBean
					.buscaPorDescricao(descricaoPesquisa);
			if (itens.isEmpty())
				FacesUtils
					.showError("Nenhum item encontrado com a descrição informada.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao fazer a pesquisa.");
		}
	}
	
	public void novoItem() {
        item = new Item();
    }
	
	public void salvaItem() {
		try {
			if (item.getId() == null) {
				itensBean.salvaItem(item);
				item = new Item();
				FacesUtils.showInfo("Item cadastrado com sucesso!");
			} else {
				itensBean.atualizaItem(item);
				FacesUtils.showInfo("Item atualizado com sucesso!");
			}
		} catch (Exception e) {
			FacesUtils.showError("Ocorreu um erro ao cadastrar o Item.");
		}
	}
	
	public void openDlgEditar(Item item) {
		this.item = item;
		PrimeFaces.current().executeScript("PF('itensDialog').show()");
		
	}
	
	@PostConstruct
	public void init() {
		recursos = recursosBean.buscaRecursos();
	}
		
	/*
	 * Auto-generated
	 */
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
	
	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
}
