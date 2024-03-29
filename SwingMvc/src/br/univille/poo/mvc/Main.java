package br.univille.poo.mvc;

import br.univille.poo.mvc.control.CadastroLivroControl;
import br.univille.poo.mvc.model.LivroModel;
import br.univille.poo.mvc.view.CadastroLivroView;

public class Main {

	public static void main(String[] args) {
		LivroModel p = new LivroModel();
		p.setId(593);
		p.setNome("O Iluminado");
		p.setCategoria("Suspense");
		p.setAutor("Stephen King");
		
		CadastroLivroControl control = new CadastroLivroControl(new CadastroLivroView(),p);
		control.exibirTela();
	}

}
