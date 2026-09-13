package br.com.fuctura.respository;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.domain.Autor;

public interface AutorRepositorio {
	void cadastrar(Autor autor) throws SQLException;
	List<Autor> consultarTodos() throws SQLException;
}
