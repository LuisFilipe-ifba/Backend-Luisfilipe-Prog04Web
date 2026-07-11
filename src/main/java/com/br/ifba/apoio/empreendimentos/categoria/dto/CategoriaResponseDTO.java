package com.br.ifba.apoio.empreendimentos.categoria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO {

    private Long id; //id ainda visivel pois é necessario para encontrar a categoria
    private String nome;
    private String descricao;
}
