package br.com.fuctura.biblioteca.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarCategoriaQuandoIdExistir() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setId(1);
        categoria.setNome("Drama");
        categoria.setDescricao("Categoria de filmes dramáticos");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        // Act
        Categoria resultado = categoriaService.buscarPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Drama", resultado.getNome());
        assertEquals("Categoria de filmes dramáticos", resultado.getDescricao());
        verify(categoriaRepository, times(1)).findById(1);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExistir() {
        // Arrange
        when(categoriaRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> {
            categoriaService.buscarPorId(99);
        });

        assertEquals("Categoria não encontrada com este id: 99", ex.getMessage());
        verify(categoriaRepository, times(1)).findById(99);
    }

    @Test
    void deveRetornarListaDeCategoriasQuandoNomeExistir() {
        // Arrange
        Categoria cat1 = new Categoria();
        cat1.setId(1);
        cat1.setNome("Ficção Científica");
        cat1.setDescricao("Categoria de filmes de ficção");

        Categoria cat2 = new Categoria();
        cat2.setId(2);
        cat2.setNome("Ficção Científica Moderna");
        cat2.setDescricao("Categoria de filmes modernos de ficção");

        when(categoriaRepository.findByNomeContainingIgnoreCase("ficção"))
                .thenReturn(Arrays.asList(cat1, cat2));

        // Act
        List<Categoria> resultado = categoriaService.buscarPorNome("ficção");

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Ficção Científica", resultado.get(0).getNome());
        verify(categoriaRepository, times(1)).findByNomeContainingIgnoreCase("ficção");
    }

    @Test
    void deveLancarExcecaoQuandoNenhumaCategoriaEncontrada() {
        // Arrange
        when(categoriaRepository.findByNomeContainingIgnoreCase("terror"))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> {
            categoriaService.buscarPorNome("terror");
        });

        assertEquals("Nenhuma categoria encontrada com este nome: terror", ex.getMessage());
        verify(categoriaRepository, times(1)).findByNomeContainingIgnoreCase("terror");
    }
        @Test
        void deveRetornarListaDeCategoriasQuandoExistirem() {
            // Arrange
            Categoria cat1 = new Categoria();
            cat1.setId(1);
            cat1.setNome("Ação");
            cat1.setDescricao("Filmes de ação");

            Categoria cat2 = new Categoria();
            cat2.setId(2);
            cat2.setNome("Comédia");
            cat2.setDescricao("Filmes de comédia");

            when(categoriaRepository.findAll()).thenReturn(Arrays.asList(cat1, cat2));

            // Act
            List<Categoria> resultado = categoriaService.buscarTodos();

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
            assertEquals("Ação", resultado.get(0).getNome());
            verify(categoriaRepository, times(1)).findAll();
        }

        @Test
        void deveLancarExcecaoQuandoNaoExistiremCategorias() {
            // Arrange
            when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());

            // Act & Assert
            ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> {
                categoriaService.buscarTodos();
            });

            assertEquals("Não existem categorias cadastradas", ex.getMessage());
            verify(categoriaRepository, times(1)).findAll();
        }

}

