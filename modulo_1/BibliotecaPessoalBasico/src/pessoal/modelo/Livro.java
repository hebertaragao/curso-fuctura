package pessoal.modelo;
public class Livro extends Item {
	private String isbn;
	private int anoPublicacao;

	public Livro(String titulo, String autorArtista, Categoria categoria, String isbn, int anoPublicacao) {
		super(titulo, autorArtista, categoria);
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	@Override
	public String getTipo() {
		return "Livro";
	}

	@Override
	public String toString() {
		return super.toString() + ", Tipo: Livro, ISBN: " + isbn + ", Ano: " + anoPublicacao;
	}
}
