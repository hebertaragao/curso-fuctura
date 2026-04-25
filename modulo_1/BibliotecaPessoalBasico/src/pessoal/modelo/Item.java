package pessoal.modelo;

public abstract class Item {
	
	private String titulo;
	private String autorArtista;
	private Categoria categoria;
	
	public Item(String titulo, String autorArtista, Categoria categoria) {
		this.titulo = titulo;
		this.autorArtista = autorArtista;
		this.categoria = categoria;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo() {
		this.titulo = titulo;
	}
	
	public String getAutorArtista() {
		return autorArtista;
	}
	
	public void setAutorArtista() {
		this.autorArtista = autorArtista;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria() {
		this.categoria = categoria;
	}
	
	public abstract String getTipo();
	
	@Override
	public String toString() {
		return "Titulo: " + titulo + ", Autor/Artista: " + autorArtista + ", Categoria: " + categoria.getNome();
	}

}
