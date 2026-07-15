package com.br.ifba.apoio.empreendimentos.projeto.repository;

import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByUsuario(Usuario Usuario);

    List<Projeto> findByStatus(String status);
}