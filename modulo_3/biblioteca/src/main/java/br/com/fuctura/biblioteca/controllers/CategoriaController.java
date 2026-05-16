package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
