package com.br.ifba.apoio.empreendimentos.mensagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRequestDTO {

    @NotNull(message = "A mensagem é obrigatória")
    @NotBlank(message = "A mensagem não pode ser vazia")
    @Size(max = 1000, message = "A mensagem deve ter no máximo 1000 caracteres")
    private String mensagem;
}