package br.com.fuctura.respository;

import java.sql.SQLException;

import java.util.List;

import br.com.fuctura.domain.Livro;

public interface LivroRepository {
	
	void cadastrar(Livro livro) throws SQLException;
	
	List<Livro> buscarTodos() throws SQLException;
	
	List<Livro> consultarPorTitulo(Livro livro) throws SQLException;

}
