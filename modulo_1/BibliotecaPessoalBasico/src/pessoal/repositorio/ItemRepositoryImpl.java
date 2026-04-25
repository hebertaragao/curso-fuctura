package pessoal.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pessoal.modelo.Item;

public class ItemRepositoryImpl implements ItemRepository {

	private List<Item> itens;

	public ItemRepositoryImpl() {
		this.itens = new ArrayList<>();
	}

	@Override
	public void adicionarItem(Item item) {
		this.itens.add(item);
	}

	@Override
	public List<Item> listarTodosItens() {
		return new ArrayList<>(this.itens);
	}

	@Override
	public List<Item> listarItensPorCategoria(String categoria) {
		return this.itens.stream().filter(item -> item.getCategoria().getNome().equalsIgnoreCase(categoria))
				.collect(Collectors.toList());
	}

	@Override
	public boolean removerItem(String titulo) {
		return this.itens.removeIf(item -> item.getTitulo().equalsIgnoreCase(titulo));
	}
}
