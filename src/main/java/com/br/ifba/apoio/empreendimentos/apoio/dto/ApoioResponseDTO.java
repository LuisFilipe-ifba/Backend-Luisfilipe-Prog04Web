package com.br.ifba.apoio.empreendimentos.apoio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoioResponseDTO {

    private Double valor;
    private LocalDateTime data;
    private String tituloProjeto;
    private String nomeApoiador;
}