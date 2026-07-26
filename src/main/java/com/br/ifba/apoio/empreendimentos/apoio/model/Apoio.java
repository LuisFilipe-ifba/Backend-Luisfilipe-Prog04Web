package com.br.ifba.apoio.empreendimentos.apoio.model;

import com.br.ifba.apoio.empreendimentos.infrastructure.model.PersistenceEntity;
import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Registra um aporte financeiro de um Usuario a um Projeto.
 * Sem "mensagem" de propósito: isso fica para quando o módulo de
 * mensagens for implementado futuramente.
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apoios")
public class Apoio extends PersistenceEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(nullable = false)
    private Double valor;

    @CreationTimestamp
    @Column(name = "data", updatable = false)
    private LocalDateTime data;
}