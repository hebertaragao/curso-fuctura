package br.com.fuctura.biblioteca.repositories;

import br.com.fuctura.biblioteca.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findAllByCategoriaId(Integer categoriaId);

    List<Livro> findAllByCategoriaNome(String categoriaNome);

}
