package com.br.ifba.apoio.empreendimentos.mensagem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemResponseDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime data;
    private String tituloProjeto;
    private String nomeAutor;
}