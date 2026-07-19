package com.br.ifba.apoio.empreendimentos.sessao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoResponseDTO {

    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private boolean ativa;
}