package com.br.ifba.apoio.empreendimentos.categoria.repository;

import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}