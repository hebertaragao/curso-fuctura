package br.com.fuctura.console;

import java.sql.SQLException;
import java.util.List;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import br.com.fuctura.domain.Livro;
import br.com.fuctura.service.LivroService;

public class MenuPrincipal {

	private LineReader reader;

	public MenuPrincipal() throws Exception {
		Terminal terminal = TerminalBuilder.builder().system(true).build();
		reader = LineReaderBuilder.builder().terminal(terminal).build();
	}

	public void iniciar() throws SQLException {
		String opcao;

		do {
			System.out.println("\n====== 📚 SISTEMA DE BIBLIOTECA ======");
			System.out.println("1) Gerenciar Livros");
			System.out.println("2) Gerenciar Usuários");
			System.out.println("3) Registrar Empréstimo (futuro)");
			System.out.println("4) Registrar Devolução (futuro)");
			System.out.println("0) Sair");
			System.out.println("=====================================");

			opcao = reader.readLine("Selecione uma opção: ");

			switch (opcao) {
			case "1" -> menuLivros();
			case "2" -> menuUsuarios();
			case "3" -> System.out.println(">>> Empréstimo (a implementar)");
			case "4" -> System.out.println(">>> Devolução (a implementar)");
			case "0" -> System.out.println("Encerrando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (!opcao.equals("0"));
	}

	private void menuLivros() throws SQLException {
		String opcao;

		do {
			System.out.println("\n------ MENU LIVROS ------");
			System.out.println("1) Cadastrar Livro");
			System.out.println("2) Listar Livros");
			System.out.println("3) Buscar Livro Por Titulo");
			System.out.println("0) Voltar");

			opcao = reader.readLine("Escolha: ");

			switch (opcao) {
			case "1" -> cadastrarLivro();
			case "2" -> listarLivros();
			case "3" -> consultarPorTitulo();
			case "0" -> System.out.println("Voltando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (!opcao.equals("0"));
	}

	private void menuUsuarios() {
		String opcao;

		do {
			System.out.println("\n------ MENU USUÁRIOS ------");
			System.out.println("1) Cadastrar Usuário");
			System.out.println("2) Listar Usuários");
			System.out.println("0) Voltar");

			opcao = reader.readLine("Escolha: ");

			switch (opcao) {
			case "0" -> System.out.println("Voltando...");
			default -> System.out.println("Opção inválida!");
			}

		} while (!opcao.equals("0"));
	}

	private void cadastrarLivro() throws SQLException {
		String nomeLivro = reader.readLine("Digite o nome do Livro: ");

		String qtdPagina = reader.readLine("Digite a qtd Página: ");
		
		String nomeAutor = reader.readLine("Digite o nome do Autor");
		
		LivroService service = new LivroService();

		service.cadastrar(nomeLivro, nomeAutor, qtdPagina);
	}

	private void listarLivros() throws SQLException {
		LivroService service = new LivroService();

		List<Livro> livros = service.consultarTodos();

		for (Livro l : livros) {
			System.out.println("ID: " + l.getId());
			System.out.println("Titulo: " + l.getTitulo());
			System.out.println("Nome Autor: "+ l.getAutor().getNome());
		}
	}

	private void consultarPorTitulo() throws SQLException {
		String titulo = reader.readLine("Digite os 3 primeiros caracteres do título do livro: ");

		if (titulo.length() > 3) {
			System.out.println("Ultrapassou o limite!");
			reader.readLine("Digite os 3 primeiros caracteres do título do livro: ");
		}

		LivroService service = new LivroService();

		List<Livro> livros = service.consultarPorTitulo(titulo);

		for (Livro l : livros) {
			System.out.println("ID: " + l.getId());
			System.out.println("Titulo: " + l.getTitulo());
		}
	}

}