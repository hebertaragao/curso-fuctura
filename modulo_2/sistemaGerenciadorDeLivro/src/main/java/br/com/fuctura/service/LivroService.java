package br.com.fuctura.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.dao.LivroDAO;
import br.com.fuctura.domain.Autor;
import br.com.fuctura.domain.Livro;
import br.com.fuctura.respository.LivroRepository;
import br.com.fuctura.respository.impl.LivroRepositoryImpl;

public class LivroService {

	public void cadastrar(String nomeLivro, String nomeDoAutor, String qtdPagina) throws SQLException {
		LivroRepository dao = new LivroRepositoryImpl();

		Livro livro = new Livro();
		livro.setTitulo(nomeLivro);
		livro.setQtdPagina(Integer.valueOf(qtdPagina));
		

		Autor autor = new Autor();
		autor.setNome(nomeDoAutor);
		
		livro.setAutor(autor);
		
		dao.cadastrar(livro);
	}
	
	public List<Livro> consultarTodos() throws SQLException{
		LivroRepository dao = new LivroRepositoryImpl();
		return dao.buscarTodos();
	}

	public List<Livro> consultarPorTitulo(String titulo) throws SQLException {
		LivroDAO dao = new LivroDAO();

		Livro livro = new Livro();
		livro.setTitulo(titulo);
		
		return dao.consultarPorTitulo(livro);
	}
}
