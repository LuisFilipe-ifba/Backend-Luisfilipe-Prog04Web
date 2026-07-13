package com.br.ifba.apoio.empreendimentos.perfil.repository;

import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByDescricao(String descricao);
}