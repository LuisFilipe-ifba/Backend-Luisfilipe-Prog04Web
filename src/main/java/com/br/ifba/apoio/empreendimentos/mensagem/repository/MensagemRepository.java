package com.br.ifba.apoio.empreendimentos.mensagem.repository;

import com.br.ifba.apoio.empreendimentos.mensagem.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findByProjeto_IdOrderByDataAsc(Long projetoId);

    List<Mensagem> findByUsuario_IdOrderByDataDesc(Long usuarioId);
}