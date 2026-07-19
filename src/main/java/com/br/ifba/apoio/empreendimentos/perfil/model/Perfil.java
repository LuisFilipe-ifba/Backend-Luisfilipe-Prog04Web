package com.br.ifba.apoio.empreendimentos.perfil.model;

import com.br.ifba.apoio.empreendimentos.infrastructure.model.PersistenceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um perfil de acesso (ex: ADMIN, EMPREENDEDOR, APOIADOR),
 * usado pelo Spring Security para autorização (roles/permissões).
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfis")
public class Perfil extends PersistenceEntity {

    @Column(nullable = false, unique = true)
    private String descricao;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfil_permissoes", joinColumns = @JoinColumn(name = "perfil_id"))
    @Column(name = "permissao")
    private List<String> permissoes = new ArrayList<>();
}