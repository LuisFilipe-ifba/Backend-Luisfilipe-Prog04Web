package com.br.ifba.apoio.empreendimentos.apoio.repository;

import com.br.ifba.apoio.empreendimentos.apoio.model.Apoio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApoioRepository extends JpaRepository<Apoio, Long> {

    List<Apoio> findByUsuario_Id(Long usuarioId);

    List<Apoio> findByProjeto_Id(Long projetoId);
}