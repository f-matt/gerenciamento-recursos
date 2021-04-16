package br.edu.facthus.agendamento.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.facthus.agendamento.entity.Categoria;

@Stateless
public class CategoriasBean {

	public List<Categoria> buscaCategorias() {
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
		
		Categoria c4 = new Categoria();
		c4.setId(4);
		c4.setDescricao("Materiais Didáticos");
		c4.setAtiva(false);

		List<Categoria> categorias = new ArrayList<>();
		categorias.add(c1);
		categorias.add(c2);
		categorias.add(c3);
		categorias.add(c4);
		
		return categorias;
	}

}
