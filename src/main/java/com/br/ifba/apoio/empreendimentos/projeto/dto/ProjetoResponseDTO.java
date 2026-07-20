package com.br.ifba.apoio.empreendimentos.projeto.dto;

import com.br.ifba.apoio.empreendimentos.projeto.model.StatusProjeto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de saída de um Projeto. Não expõe "id" nem "criadorId".
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoResponseDTO {

    private String titulo;
    private String descricao;
    private Double valorNecessario;
    private Double valorArrecadado;
    private StatusProjeto status;
    private LocalDateTime dataCriacao;
    private String categoria;
}