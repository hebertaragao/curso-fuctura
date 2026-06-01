package br.com.fuctura.biblioteca.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@JsonPropertyOrder({"id","nome","descricao"})
public class CategoriaDto {

    private Integer id;

    @NotNull(message = "O nome da categoria não pode ser nulo.")
    @Length(min = 3, max = 15, message = "O nome da categoria deve conter entre 3 e 15 caracteres.")
    private String nome;

    @NotNull(message = "O nome descrição não pode ser nulo.")
    @Length(min = 10, max = 50, message = "O campo descrição deve conter no entre 10 e 50 caracteres.")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
