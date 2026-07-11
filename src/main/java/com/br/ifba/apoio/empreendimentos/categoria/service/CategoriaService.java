package com.br.ifba.apoio.empreendimentos.categoria.service;

import com.br.ifba.apoio.empreendimentos.categoria.model.Categoria;
import com.br.ifba.apoio.empreendimentos.categoria.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + id));
    }

    public Categoria atualizarCategoria(Long id, Categoria dadosAtualizados) {
        Categoria existente = buscarPorId(id);

        existente.setNome(dadosAtualizados.getNome());
        existente.setDescricao(dadosAtualizados.getDescricao());

        return categoriaRepository.save(existente);
    }

    public void deletarCategoria(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}