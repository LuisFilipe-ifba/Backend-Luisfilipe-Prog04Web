package com.br.ifba.apoio.empreendimentos.categoria.model;

import com.br.ifba.apoio.empreendimentos.infrastructure.model.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorias")
public class Categoria extends PersistenceEntity {

    @Column(nullable = false)
    private String nome;

    private String descricao;

}
