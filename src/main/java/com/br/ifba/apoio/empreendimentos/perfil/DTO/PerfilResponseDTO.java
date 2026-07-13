package com.br.ifba.apoio.empreendimentos.perfil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDTO {

    private Long id; //visivel pois é necessario para criar um usuario
    private String descricao;
    private List<String> permissoes;
}