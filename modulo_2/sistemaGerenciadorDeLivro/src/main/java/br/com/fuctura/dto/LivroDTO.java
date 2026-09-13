package br.com.fuctura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO=> Objeto de Transferencia de Dados
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
	private int qtd;
	private String tituloDoLivro;
}

