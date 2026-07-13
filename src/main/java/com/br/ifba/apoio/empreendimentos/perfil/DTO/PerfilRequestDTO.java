package com.br.ifba.apoio.empreendimentos.perfil.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilRequestDTO {

    @NotNull(message = "A descrição é obrigatória")
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotEmpty(message = "O perfil deve ter ao menos uma permissão")
    private List<String> permissoes;
}