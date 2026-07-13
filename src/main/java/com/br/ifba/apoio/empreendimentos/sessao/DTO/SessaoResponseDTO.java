package com.br.ifba.apoio.empreendimentos.sessao.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoResponseDTO {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private boolean ativa;
}