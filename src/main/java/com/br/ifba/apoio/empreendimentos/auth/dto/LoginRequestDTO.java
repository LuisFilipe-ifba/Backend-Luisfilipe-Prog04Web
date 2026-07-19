package com.br.ifba.apoio.empreendimentos.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "O email é obrigatório")
    @NotBlank(message = "O email não pode ser vazio")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;
}