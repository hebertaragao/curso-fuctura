package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LivroController {

    @Autowired
    private LivroService livroService;
}
