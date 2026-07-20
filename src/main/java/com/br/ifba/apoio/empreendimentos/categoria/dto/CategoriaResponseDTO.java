package com.br.ifba.apoio.empreendimentos.categoria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO {

    private String nome;
    private String descricao;
}