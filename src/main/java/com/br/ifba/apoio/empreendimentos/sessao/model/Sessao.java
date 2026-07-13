package com.br.ifba.apoio.empreendimentos.sessao.model;

import com.br.ifba.apoio.empreendimentos.infrastructure.model.PersistenceEntity;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Registro de um token JWT emitido para um usuário.
 *
 * Um JWT em si já é "stateless" (o próprio token carrega a validade), mas
 * persistir a Sessão permite: (1) fazer logout de verdade invalidando um
 * token antes da expiração natural, e (2) auditar de onde/quando o usuário
 * logou. Sem essa tabela, uma vez emitido, um JWT só perde validade quando expira.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessoes")
public class Sessao extends PersistenceEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    @Column(nullable = false)
    private boolean ativa = true;
}