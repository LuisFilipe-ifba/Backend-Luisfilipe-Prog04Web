package com.br.ifba.apoio.empreendimentos.usuario.model;

import com.br.ifba.apoio.empreendimentos.infrastructure.model.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario extends PersistenceEntity {


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private boolean ativo;

    //private perfil perfilUsuario
}
