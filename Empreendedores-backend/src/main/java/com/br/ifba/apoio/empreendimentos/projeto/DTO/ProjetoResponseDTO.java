package com.br.ifba.apoio.empreendimentos.projeto.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoResponseDTO {

    private String titulo;
    private String descricao;
    private Double valorNecessario;
    private Double valorArrecadado;
    private String status;
    private LocalDateTime dataCriacao;
    private String nomeCriador;
    private List<String> categorias;
}