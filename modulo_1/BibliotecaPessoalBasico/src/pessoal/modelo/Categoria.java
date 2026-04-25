package pessoal.modelo;

public class Categoria {
	
	private String nome;
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome() {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
