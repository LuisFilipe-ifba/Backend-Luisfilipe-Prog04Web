package com.br.ifba.apoio.empreendimentos.projeto.model;

import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;
import com.br.ifba.apoio.empreendimentos.infrastructure.model.PersistenceEntity;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projetos")
public class Projeto extends PersistenceEntity {

    @Column(nullable = false)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CriadorId", nullable = false)
    private Usuario criador;

    @Column(nullable = false, length = 2000)
    private String descricao;

    @Column(nullable = false)
    private Double valorNecessario;

    @Column(nullable = false)
    private Double valorArrecadado = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProjeto status = StatusProjeto.EM_ANALISE;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}