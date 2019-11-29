package br.univille.poo.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.univille.poo.mvc.model.LivroModel;

public class LivroModelDAO extends BasicoDAO{
	
	public void insert(LivroModel livro) {

		String sql = " insert into livros(id, nome, categoria, autor) values(?,?,?,?)";
		
		try (Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setLong(1, livro.getId());
			statement.setString(2, livro.getNome());
			statement.setString(3, livro.getCategoria());
			statement.setString(4, livro.getAutor());
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(LivroModel livro) {
		
		String sql = " delete from livros where id = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setLong(1, livro.getId());
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(LivroModel livro) {
		String sql = " update livros set nome = ?, categoria = ?, autor = ? where id = ?";
		
		try (Connection conn = getConnection();
			 PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, livro.getNome());
			statement.setString(2, livro.getCategoria());
			statement.setString(3, livro.getAutor());
			statement.setLong(4, livro.getId());
			statement.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LivroModel getById(int id) {
		LivroModel LivroModel = null;
		String sql = " select id, nome, categoria, autor from livros where id = ?";
		
		try(Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				LivroModel = new LivroModel();
				LivroModel.setId(resultSet.getLong(1));
				LivroModel.setNome(resultSet.getString(2));
				LivroModel.setCategoria(resultSet.getString(3));
				LivroModel.setAutor(resultSet.getString(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return LivroModel;
	}
	
}
