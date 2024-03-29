package br.univille.poo.mvc.control;

import br.univille.poo.mvc.model.LivroModel;
import br.univille.poo.mvc.view.CadastroLivroView;

public class CadastroLivroControl {
	
	private CadastroLivroView view;
	private LivroModel model;
	
	public CadastroLivroControl(CadastroLivroView view, LivroModel model) {
		this.view = view;
		this.model = model;
		model.attach(view);
		view.setModel(model);
		view.setControl(this);
	}
	
	public void exibirTela() {
		view.show();
		model.notifyObservers();
	}

	public void deletar() {
		model.deletar();
		view.setMensagemStatusBar("Registro deletado.");
		view.getBotaoDeletar().setEnabled(false);
	}

	public void novo() {
		model.novoRegistro();
		view.getBotaoDeletar().setEnabled(false);
		view.setMensagemStatusBar("Registro novo.");
	}
	
	public void salvar() {
		model.setAutor(view.getAutor());
		model.setCategoria(view.getCategoria());
		model.setNome(view.getNome());
		try {
			model.salvar();
			view.setMensagemStatusBar("Registro salvo com sucesso.");
			view.getBotaoDeletar().setEnabled(true);
		}catch(Exception e) {
			e.printStackTrace();
			view.setMensagemStatusBar("Erro: "+e.getMessage());
		}
	}

}
