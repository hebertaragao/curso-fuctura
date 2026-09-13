package br.com.fuctura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fuctura.domain.Livro;
import br.com.fuctura.infrastructura.ConnectionFactory;
import br.com.fuctura.respository.LivroRepository;

public class LivroDAO implements LivroRepository  {

	// COMANDOS DE SQL
	public List<Livro> consultarTodos() throws SQLException {

		ConnectionFactory fabrica = new ConnectionFactory();

		Connection conexao = fabrica.getConnection();

		String comandoSQL = "select * from livro";

		PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

		ResultSet rs = pstm.executeQuery();

		List<Livro> resultadoConsulta = new ArrayList<Livro>();

		while (rs.next()) {
			int id = rs.getInt("id");
			String titulo = rs.getString("titulo");

			Livro l = new Livro();
			l.setTitulo(titulo);
			l.setId(id);

			resultadoConsulta.add(l);
		}

		return resultadoConsulta;
	}

	public Livro consultarPorId(Livro livro) {
		return null;
	}

	public void atualizar(Livro livro) {
	}

	public void excluir(Livro livro) {
	}

	public void cadastrar(Livro livro) throws SQLException {
		// insert into livro (titulo) values(?)

		ConnectionFactory fabrica = new ConnectionFactory();

		Connection conexao = fabrica.getConnection();

		String comandoSQL = "insert into livro (titulo) values(?)";

		PreparedStatement stm = conexao.prepareStatement(comandoSQL);

		stm.setString(1, livro.getTitulo());

		stm.execute();// play

	}

	public List<Livro> consultarPorTitulo(Livro livro) throws SQLException {
		ConnectionFactory fabrica = new ConnectionFactory(); //repetido

		Connection conexao = fabrica.getConnection(); //repetido

		String comandoSQL = "select * from livro where titulo like ?";

		PreparedStatement stm = conexao.prepareStatement(comandoSQL); //repetido

		stm.setString(1, livro.getTitulo() + "%");

		ResultSet rs = stm.executeQuery(); //repetido

		List<Livro> resultadoConsulta = new ArrayList<Livro>(); //repetido

		//repetido
		while (rs.next()) {
			int id = rs.getInt("id");
			String titulo = rs.getString("titulo");

			Livro l = new Livro();
			l.setTitulo(titulo);
			l.setId(id);

			resultadoConsulta.add(l);
		}

		return resultadoConsulta;
	}

	@Override
	public List<Livro> buscarTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
