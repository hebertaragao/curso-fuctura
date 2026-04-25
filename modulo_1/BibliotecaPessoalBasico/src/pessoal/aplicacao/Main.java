package pessoal.aplicacao;

import pessoal.modelo.AlbumMusica;
import pessoal.modelo.Categoria;
import pessoal.modelo.Livro;
import pessoal.modelo.Revista;
import pessoal.servico.BibliotecaService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static BibliotecaService service = new BibliotecaService();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao;
		do {
			exibirMenu();
			opcao = lerOpcao();
			switch (opcao) {
			case 1:
				adicionarItem();
				break;
			case 2:
				listarItens();
				break;
			case 3:
				listarItensPorCategoria();
				break;
			case 4:
				removerItem();
				break;
			case 0:
				System.out.println("Saindo do programa. Até mais!");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
			System.out.println(); // Linha em branco para melhor visualização
		} while (opcao != 0);
		scanner.close();
	}

	private static void exibirMenu() {
		System.out.println("=== Organizador de Biblioteca Pessoal ===");
		System.out.println("1. Adicionar Item");
		System.out.println("2. Listar Todos os Itens");
		System.out.println("3. Listar Itens por Categoria");
		System.out.println("4. Remover Item");
		System.out.println("0. Sair");
		System.out.print("Escolha uma opção: ");
	}

	private static int lerOpcao() {
		try {
			return scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Entrada inválida. Por favor, digite um número.");
			scanner.next(); // Limpa o buffer do scanner
			return -1; // Retorna uma opção inválida
		}
	}

	private static void adicionarItem() {
		scanner.nextLine(); // Consumir a nova linha pendente
		System.out.println("\n==== Adicionar Novo Item ====");
		System.out.print("Título: ");
		String titulo = scanner.nextLine();
		System.out.print("Autor/Artista: ");
		String autorArtista = scanner.nextLine();
		System.out.print("Categoria (ex: Romance, Tecnologia, Rock): ");
		String nomeCategoria = scanner.nextLine();
		Categoria categoria = new Categoria(nomeCategoria);
		System.out.println("Tipo de Item (1-Livro, 2-Revista, 3-Álbum de Música): ");
		int tipoItem = lerOpcao();
		scanner.nextLine(); // Consumir a nova linha pendente
		try {
			switch (tipoItem) {
			case 1:
			System.out.print("ISBN: ");
			String isbn = scanner.nextLine();
			System.out.print("Ano de Publicação: ");
			int anoPublicacao = scanner.nextInt();
			service.adicionarItem(new Livro(titulo, autorArtista, categoria, isbn, anoPublicacao));
			break;
			case 2:
			System.out.print("Número da Edição: ");
			int numeroEdicao = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha pendente
			System.out.print("Data de Publicação (DD/MM/AAAA): ");
			String dataPublicacao = scanner.nextLine();
			service.adicionarItem(new Revista(titulo, autorArtista, categoria, numeroEdicao, dataPublicacao));
			break;
			case 3:
			System.out.print("Número de Faixas: ");
			int numeroFaixas = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha pendente
			System.out.print("Gênero: ");
			String genero = scanner.nextLine();
			service.adicionarItem(new AlbumMusica(titulo, autorArtista, categoria, numeroFaixas, genero));
			break;
			default:
			System.out.println("Tipo de item inválido.");
			}
			} catch (IllegalArgumentException e) {
			System.out.println("Erro ao adicionar item: " + e.getMessage());
			} catch (InputMismatchException e) {
			System.out.println("Entrada inválida para campos numéricos. Por favor, tente novamente.");
			scanner.next(); // Limpa o buffer do scanner
			}
			}

	private static void listarItens() {
		System.out.println("\n==== Todos os Itens Cadastrados ====");
		java.util.List<pessoal.modelo.Item> itens = service.listarTodosItens();
		if (itens.isEmpty()) {
			System.out.println("Nenhum item cadastrado.");
		} else {
			for (pessoal.modelo.Item item : itens) {
				System.out.println(item);
			}
		}
	}

	private static void listarItensPorCategoria() {
		scanner.nextLine(); // Consumir a nova linha pendente
		System.out.println("\n==== Listar Itens por Categoria ====");
		System.out.print("Digite a categoria para filtrar: ");
		String categoria = scanner.nextLine();
		try {
			java.util.List<pessoal.modelo.Item> itens = service.listarItensPorCategoria(categoria);
			if (itens.isEmpty()) {
				System.out.println("Nenhum item encontrado para a categoria \'" + categoria + "\'.");
			} else {
				for (pessoal.modelo.Item item : itens) {
					System.out.println(item);
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao listar por categoria: " + e.getMessage());
		}
	}

	private static void removerItem() {
		scanner.nextLine(); // Consumir a nova linha pendente
		System.out.println("\n==== Remover Item ====");
		System.out.print("Digite o título do item a ser removido: ");
		String titulo = scanner.nextLine();
		try {
			service.removerItem(titulo);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao remover item: " + e.getMessage());
		}
	}

}
