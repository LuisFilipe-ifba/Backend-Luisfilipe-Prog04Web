package com.br.ifba.apoio.empreendimentos.sessao.repository;

import com.br.ifba.apoio.empreendimentos.sessao.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Optional<Sessao> findByTokenAndAtivaTrue(String token);

    Optional<Sessao> findByToken(String token);

    List<Sessao> findByUsuario_IdAndAtivaTrue(Long usuarioId);
}