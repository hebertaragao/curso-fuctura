package br.com.fuctura.respository.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.fuctura.domain.Livro;
import br.com.fuctura.dto.LivroDTO;
import br.com.fuctura.infrastructura.ConnectionFactory;
import br.com.fuctura.respository.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class LivroRepositoryImpl implements LivroRepository {

	@Override
	public void cadastrar(Livro livro) throws SQLException {
		ConnectionFactory fabrica = new ConnectionFactory();
		
		EntityManagerFactory emf = fabrica.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(livro); 
		em.getTransaction().commit();
	}

	@Override
	public List<Livro> buscarTodos() throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		EntityManagerFactory entityManagerFactory = connectionFactory.getEntityManagerFactory();
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String comandoJPQL = "select abc from Livro abc";
		
		List<Livro> resultadoConsulta = em
				.createQuery(comandoJPQL, Livro.class)
				.getResultList();
		
		em.close();
		
		//SELECT * FROM tb_livro
		return resultadoConsulta;
	}

	@Override
	public List<Livro> consultarPorTitulo(Livro livro) throws SQLException {
		EntityManagerFactory emf = ConnectionFactory
				.getEntityManagerFactory();
		
		EntityManager em = emf.createEntityManager();
		
		//select * from livro where titulo like ?
		
		String comandoJPQL = "SELECT l FROM Livro l where titulo like :fuctura";
		
		List<Livro> resultadoConsulta = em
				.createQuery(comandoJPQL, Livro.class)
				.setParameter("fuctura", livro.getTitulo() + "%")
				.getResultList();
		
		return resultadoConsulta;
	}

	
	public List<LivroDTO> buscarQtdLivro() throws SQLException {
		EntityManagerFactory entityManagerFactory = ConnectionFactory.getEntityManagerFactory();
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String comandoJPQL = "select new br.com.fuctura.dto.LivroDTO(count(qtdpagina), titulo) \n"
				+ "from Livro\n"
				+ "group by titulo\n";
		
		List<LivroDTO> resultadoConsulta = em
				.createQuery(comandoJPQL, LivroDTO.class)
				.getResultList();
		
		em.close();
		
		return resultadoConsulta;
	}
	
}
