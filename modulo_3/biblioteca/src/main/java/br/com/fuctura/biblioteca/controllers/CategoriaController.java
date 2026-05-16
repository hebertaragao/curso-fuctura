package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable int id) {
        Categoria cat = categoriaService.buscarPorId(id);
        return cat;
    }

    @GetMapping
    public List<Categoria> buscarTodos() {
        List<Categoria> list = categoriaService.buscarTodos();
        return list;
    }

    @PostMapping
    public Categoria salvar( @RequestBody Categoria categoria) {
        return categoriaService.salvar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria cat = categoriaService.atualizar(categoria);
        return cat;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        categoriaService.deletar(id);
    }
}
