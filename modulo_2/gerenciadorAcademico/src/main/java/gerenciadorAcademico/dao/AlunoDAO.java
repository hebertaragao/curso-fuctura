package gerenciadorAcademico.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gerenciadorAcademico.domain.Aluno;

public class AlunoDAO {

	public void cadastrar(Connection conn, Aluno aluno) throws IOException, SQLException {
		String comandoSQL = "INSERT INTO aluno (matricula, nome, idade, celular) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstm = conn.prepareStatement(comandoSQL)) {
			pstm.setString(1, aluno.getMatricula());
			pstm.setString(2, aluno.getNome());
			pstm.setInt(3, aluno.getIdade());
			pstm.setString(4, aluno.getCelular());

			pstm.execute();
		}
	}

	// listar alunos
	public List<Aluno> findAll(Connection connection) {
		String comandoSQL = "SELECT * FROM aluno ORDER BY nome";
		
		List<Aluno> alunos = new ArrayList<Aluno>();

		try (PreparedStatement stmt = connection.prepareStatement(comandoSQL); ResultSet rs = stmt.executeQuery()) {
			alunos = converterToAluno(rs);
		} catch (SQLException e) {
			System.out.println("Erro ao listar alunos: " + e.getMessage());
		}

		return alunos;
	}

	public List<Aluno> findByIdade(Connection conn, int idade) throws SQLException {

		String comandoSQL = "SELECT * FROM aluno where idade > ?";

		PreparedStatement stmt = conn.prepareStatement(comandoSQL); 
		
		stmt.setInt(1, idade);
		
		ResultSet rs = stmt.executeQuery();
			
		List<Aluno> alunos = converterToAluno(rs);

		return alunos;
	}

	private List<Aluno> converterToAluno(ResultSet rs) throws SQLException {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		while (rs.next()) {
			String nome = rs.getString("nome");
			String matricula = rs.getString("matricula");
			int idadeConsulta = rs.getInt("idade");
			String celular = rs.getString("celular");

			Aluno a = new Aluno();

			a.setNome(nome);
			a.setCelular(celular);
			a.setIdade(idadeConsulta);
			a.setMatricula(matricula);

		}
		return alunos;
	}

}
