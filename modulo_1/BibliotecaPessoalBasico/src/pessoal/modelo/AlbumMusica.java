package pessoal.modelo;

public class AlbumMusica extends Item {
	private int numeroFaixas;
	private String genero;

	public AlbumMusica(String titulo, String autorArtista, Categoria categoria, int numeroFaixas, String genero) {
		super(titulo, autorArtista, categoria);
		this.numeroFaixas = numeroFaixas;
		this.genero = genero;
	}

	public int getNumeroFaixas() {
		return numeroFaixas;
	}

	public void setNumeroFaixas(int numeroFaixas) {
		this.numeroFaixas = numeroFaixas;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String getTipo() {
		return "Album de Música";
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Tipo: Álbum de Música, Faixas: " + numeroFaixas + ", Gênero: " + genero;
	}

}
