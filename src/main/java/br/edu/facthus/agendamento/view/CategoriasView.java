package br.edu.facthus.agendamento.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.facthus.agendamento.entity.Categoria;

@Named
@ViewScoped
public class CategoriasView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		// TODO: categorias criadas para teste
		Categoria c1 = new Categoria();
		c1.setId(1);
		c1.setDescricao("Informática");
		c1.setAtiva(true);
		
		Categoria c2 = new Categoria();
		c2.setId(2);
		c2.setDescricao("Audiovisual");
		c2.setAtiva(true);
		
		Categoria c3 = new Categoria();
		c3.setId(3);
		c3.setDescricao("Materiais Elétricos");
		c3.setAtiva(true);
		
		categorias = new ArrayList<>();
		categorias.add(c1);
		categorias.add(c2);
		categorias.add(c3);
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	

}
