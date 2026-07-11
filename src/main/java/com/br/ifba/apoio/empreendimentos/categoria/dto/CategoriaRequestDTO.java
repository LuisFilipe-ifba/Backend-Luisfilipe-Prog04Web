package com.br.ifba.apoio.empreendimentos.categoria.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {

    @JsonProperty("nome")
    @NotNull(message = "O nome é obrigatório")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @JsonProperty("descricao")
    @NotNull(message = "A descrição é obrigatória")
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;
}