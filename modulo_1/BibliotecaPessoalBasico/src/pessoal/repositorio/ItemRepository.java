package pessoal.repositorio;

import java.util.List;

import pessoal.modelo.Item;

public interface ItemRepository {
	
	void adicionarItem(Item item);
	List<Item> listarTodosItens();
	List<Item> listarItensPorCategoria(String categoria);
	boolean removerItem(String titulo);
}
