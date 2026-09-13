package gerenciadorAcademico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import gerenciadorAcademico.dao.AlunoDAO;
import gerenciadorAcademico.domain.Aluno;

public class Aplicacao {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123";

	private final LineReader reader;
	private final Connection connection;

	public Aplicacao() throws Exception {
		Terminal terminal = TerminalBuilder.builder().system(true).build();
		reader = LineReaderBuilder.builder().terminal(terminal).build();
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void main(String[] args) {
		try {
			new Aplicacao().iniciar();
		} catch (Exception e) {
			System.err.println("Erro ao iniciar o sistema: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void iniciar() throws NumberFormatException, SQLException {
		System.out.println("=================================");
		System.out.println("      SISTEMA ACADÊMICO");
		System.out.println("=================================");

		String opcao;
		do {
			exibirMenuPrincipal();
			opcao = lerOpcao("Escolha: ");

			switch (opcao) {
			case "1" -> menuAlunos();
			case "2" -> System.out.println("Módulo de Professores ainda não implementado.");
			case "3" -> System.out.println("Módulo de Disciplinas ainda não implementado.");
			case "4" -> System.out.println("Módulo de Matrículas ainda não implementado.");
			case "0" -> System.out.println("Encerrando...");
			default -> System.out.println("Opção inválida! Tente novamente.");
			}
		} while (!opcao.equals("0"));

		fecharConexao();
	}

	private void exibirMenuPrincipal() {
		System.out.println();
		System.out.println("1 - Alunos");
		System.out.println("2 - Professores");
		System.out.println("3 - Disciplinas");
		System.out.println("4 - Matrículas");
		System.out.println("0 - Sair");
	}

	private void menuAlunos() throws NumberFormatException, SQLException {
		String opcao;

		do {
			System.out.println();
			System.out.println("=================================");
			System.out.println("          ALUNOS");
			System.out.println("=================================");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Listar");
			System.out.println("3 - Buscar");
			System.out.println("4 - Excluir");
			System.out.println("0 - Voltar");

			opcao = lerOpcao("Escolha: ");

			switch (opcao) {
			case "1" -> cadastrarAluno();
			case "2" -> listarAlunos();
			case "3" -> buscarAluno();
			case "4" -> excluirAluno();
			case "0" -> System.out.println("Voltando ao menu principal...");
			default -> System.out.println("Opção inválida! Tente novamente.");
			}
		} while (!opcao.equals("0"));
	}

	private void cadastrarAluno() {
		try {
			String matricula = lerOpcao("Matrícula: ");
			String nome = lerOpcao("Nome: ");
			String idadeTexto = lerOpcao("Idade: ");
			String celular = lerOpcao("Celular: ");

			int idade = Integer.parseInt(idadeTexto);

			Aluno aluno = new Aluno();
			aluno.setMatricula(matricula);
			aluno.setNome(nome);
			aluno.setIdade(idade);
			aluno.setCelular(celular);

			AlunoDAO alunoDAO = new AlunoDAO();
			alunoDAO.cadastrar(connection, aluno);

			System.out.println("Aluno cadastrado com sucesso!");
		} catch (NumberFormatException e) {
			System.out.println("Idade inválida. Digite um número inteiro.");
		} catch (Exception e) {
			System.out.println("Falha ao cadastrar o aluno: " + e.getMessage());
		}
	}

	private void listarAlunos() {
		AlunoDAO dao = new AlunoDAO();

		List<Aluno> resultadoConsulta = dao.findAll(connection);

		for (Aluno a : resultadoConsulta) {
			System.out.println("Nome: " + a.getNome());
			System.out.println("Matricula: " + a.getMatricula());
		}
	}

	private void buscarAluno() throws NumberFormatException, SQLException {
		String idade = lerOpcao("Idade: ");
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> resultadoConsulta = dao.findByIdade(connection, Integer.valueOf(idade));

		for (Aluno a : resultadoConsulta) {
			System.out.println("Nome: " + a.getNome());
			System.out.println("Matricula: " + a.getMatricula());
		}

	}

	private void excluirAluno() {
		String matricula = lerOpcao("Matrícula: ");
		String sql = "DELETE FROM aluno WHERE matricula = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, matricula);
			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Aluno removido com sucesso!");
			} else {
				System.out.println("Aluno não encontrado para exclusão.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao excluir aluno: " + e.getMessage());
		}

		pausar();
	}

	private String lerOpcao(String prompt) {
		return reader.readLine(prompt).trim();
	}

	private void pausar() {
		reader.readLine("Pressione ENTER para continuar...");
	}

	private void fecharConexao() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		}
	}
}
