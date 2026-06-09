package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro buscarPorId(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundException("Livro não encontrado com este id: " + id);
    }


    public List<Livro> buscarPorCategoria(Integer categoriaId) {
        categoriaService.buscarPorId(categoriaId); // Verifica se a categoria existe
        List<Livro> list = livroRepository.findAllByCategoriaId(categoriaId);
        return list;
    }

    public Livro salvar(Livro livro, Integer categoriaId) {
        livro.setId(null);
        Categoria categoria = categoriaService.buscarPorId(categoriaId);
        livro.setCategoria(categoria);
        return livroRepository.save(livro);
    }

    public Livro atualizar(Livro livro, Integer categoriaId) {
        buscarPorId(livro.getId());
        Categoria categoria = categoriaService.buscarPorId(categoriaId);
        livro.setCategoria(categoria);
        return livroRepository.save(livro);
    }

    public void delete(Integer id) {
        buscarPorId(id);
        livroRepository.deleteById(id);
    }
}