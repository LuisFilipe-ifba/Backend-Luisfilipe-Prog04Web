package com.br.ifba.apoio.empreendimentos.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private String nome;
    private String email;
    private boolean ativo;
    private String perfil;
}