package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.LivroDto;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.services.LivroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livro")
@CrossOrigin("*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Integer id) {
        Livro livro = livroService.buscarPorId(id);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    //localhost:8082/livro?categoria=nomeCategoria
    @GetMapping
    public ResponseEntity<List<LivroDto>> buscarTodosPorCategoria(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId) {
        List<Livro> list = livroService.buscarPorCategoria(categoriaId);
        //return ResponseEntity.ok().body(list.stream().map(x -> new LivroDto(x)).toList());
        List<LivroDto> listDto = new ArrayList<>();

        for (Livro livro : list) {
            listDto.add(new LivroDto(livro));
        }
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<LivroDto> salvar(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId,
                                           @RequestBody LivroDto livroDto) {
        Livro livro = new Livro(livroDto);
        Livro l = livroService.salvar(livro, categoriaId);
        return ResponseEntity.ok().body(new LivroDto(l));
    }

    //localhost:8082/livro/1?categoria=2
    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizar(@PathVariable Integer id,
                                              @RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId,
                                              @RequestBody LivroDto livroDto) {
        livroDto.setId(id);
        Livro livro = new Livro(livroDto);
        Livro livro1 = livroService.atualizar(livro, categoriaId);
        return ResponseEntity.ok().body(new LivroDto(livro1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}