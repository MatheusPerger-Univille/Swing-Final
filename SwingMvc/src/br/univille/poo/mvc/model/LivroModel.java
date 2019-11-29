package br.univille.poo.mvc.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.univille.poo.mvc.dao.LivroModelDAO;
import br.univille.poo.mvc.util.Observer;
import br.univille.poo.mvc.util.Subject;

public class LivroModel implements  Subject{
	
	private long id;
	private String nome;
	private String categoria;
	private String autor;
	private List<Observer> list;
	private LivroModelDAO livroDao = new LivroModelDAO();
	
	public LivroModel() {
		list = new ArrayList<Observer>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	public void novoRegistro() {
		id = 0;
		nome = "";
		categoria = "";
		autor = "";
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "LivroModel [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", autor=" + autor + "]";
	}
	
	
	@Override
	public void attach(Observer o) {
		list.add(o);
	}

	@Override
	public void detach(Observer o) {
		list.remove(o);
	}

	@Override
	public void notifyObservers() {
		// Avisa cada um dos observadores
		for(Observer o : list) {
			// Atualiza a informacao no observador
			o.update(this, this);
		}
	}

	public void salvar() throws Exception {
		
		if(categoria == null || categoria.isEmpty()) {
			throw new Exception("Categoria inv�lida");
		}
		if(nome == null || nome.isEmpty()) {
			throw new Exception("Nome inv�lido");
		}
		if(autor == null || autor.isEmpty()) {
			throw new Exception("Autor em branco");
		}
		//Novo registro
		if(id == 0) {
			// Gera um id
			id = (new Random()).nextInt(100);
			this.livroDao.insert(this);
		}else {
			this.livroDao.update(this);
		}
		notifyObservers();
	}
	
	public void deletar() {
		this.livroDao.delete(this);
		novoRegistro();
		notifyObservers();
	}

}