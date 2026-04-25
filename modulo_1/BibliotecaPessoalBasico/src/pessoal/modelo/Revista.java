package pessoal.modelo;

public class Revista extends Item {
	private int numeroEdicao;
	private String dataPublicacao;

	public Revista(String titulo, String autorArtista, Categoria categoria, int numeroEdicao, String dataPublicacao) {
		super(titulo, autorArtista, categoria);
		this.numeroEdicao = numeroEdicao;
		this.dataPublicacao = dataPublicacao;
	}

	public int getNumeroEdicao() {
		return numeroEdicao;
	}

	public void setNumeroEdicao(int numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@Override
	public String getTipo() {
		return "Revista";
	}

	@Override
	public String toString() {
		return super.toString() + ", Tipo: Revista, Edição: " + numeroEdicao + ", Data: " + dataPublicacao;
	}
}