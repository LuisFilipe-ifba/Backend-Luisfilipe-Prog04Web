package com.br.ifba.apoio.empreendimentos.perfil.service;

import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import com.br.ifba.apoio.empreendimentos.perfil.repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService implements PerfilIService {

    private final PerfilRepository perfilRepository;

    @Override
    public Perfil criar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }

    @Override
    public Perfil buscarPorId(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado com id: " + id));
    }

    @Override
    public Perfil atualizar(Long id, @NonNull Perfil dadosAtualizados) {
        Perfil existente = buscarPorId(id);

        existente.setDescricao(dadosAtualizados.getDescricao());
        existente.setPermissoes(dadosAtualizados.getPermissoes());

        return perfilRepository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        Perfil perfil = buscarPorId(id);
        perfilRepository.delete(perfil);
    }
}