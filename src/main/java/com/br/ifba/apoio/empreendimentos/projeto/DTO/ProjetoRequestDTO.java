package com.br.ifba.apoio.empreendimentos.projeto.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoRequestDTO {

    @JsonProperty("titulo")
    @NotNull(message = "O título é obrigatório")
    @NotBlank(message = "O título não pode ser vazio")
    private String titulo;

    @JsonProperty("descricao")
    @NotNull(message = "A descrição é obrigatória")
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @JsonProperty("valorNecessario")
    @NotNull(message = "O valor necessário é obrigatório")
    @Positive(message = "O valor necessário deve ser maior que zero")
    private Double valorNecessario;

    @JsonProperty("categoriaIds")
    @NotEmpty(message = "O projeto deve ter ao menos uma categoria")
    private List<Long> categoriaIds;
}