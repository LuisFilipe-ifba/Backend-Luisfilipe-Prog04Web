package com.br.ifba.apoio.empreendimentos.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Não expõe "id": ele é gerado pelo banco.
 * "ativo" também não é aceito aqui: todo usuário novo nasce ativo por padrão,
 * quem desativa é uma ação administrativa separada (fora deste DTO).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotNull(message = "O nome é obrigatório")
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @NotNull(message = "O email é obrigatório")
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotNull(message = "A senha é obrigatória")
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

    private Long perfilId;
}