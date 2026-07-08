package com.br.ifba.apoio.empreendimentos.usuario.repository;

import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
