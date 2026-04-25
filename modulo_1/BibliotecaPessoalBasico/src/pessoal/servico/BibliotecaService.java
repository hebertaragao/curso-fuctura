package pessoal.servico;

import pessoal.modelo.Categoria;
import pessoal.modelo.Item;
import pessoal.repositorio.ItemRepository;
import pessoal.repositorio.ItemRepositoryImpl;
import java.util.List;

public class BibliotecaService {
	private ItemRepository itemRepository;

	public BibliotecaService() {
		this.itemRepository = new ItemRepositoryImpl();
	}

	public void adicionarItem(Item item) throws IllegalArgumentException {
		if (item.getTitulo() == null || item.getTitulo().trim().isEmpty()) {
			throw new IllegalArgumentException("Título do item é obrigatório.");
		}
		if (item.getAutorArtista() == null || item.getAutorArtista().trim().isEmpty()) {
			throw new IllegalArgumentException("Autor/Artista do item é obrigatório.");
		}
		if (item.getCategoria() == null || item.getCategoria().getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("Categoria do item é obrigatória.");
		}
		itemRepository.adicionarItem(item);
		System.out.println("Item adicionado com sucesso!");
	}

	public List<Item> listarTodosItens() {
		return itemRepository.listarTodosItens();
	}

	public List<Item> listarItensPorCategoria(String categoria) {
		if (categoria == null || categoria.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome da categoria é obrigatório para a busca.");
		}
		return itemRepository.listarItensPorCategoria(categoria);
	}

	public boolean removerItem(String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("Título do item é obrigatório para a remoção.");
		}
		if (itemRepository.removerItem(titulo)) {
			System.out.println("Item \"" + titulo + "\" removido com sucesso.");
			return true;
		} else {
			System.out.println("Item \"" + titulo + "\" não encontrado.");
			return false;
		}
	}
}
