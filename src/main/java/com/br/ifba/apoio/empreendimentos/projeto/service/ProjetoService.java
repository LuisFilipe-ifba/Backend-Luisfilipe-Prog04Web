package com.br.ifba.apoio.empreendimentos.projeto.service;

import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.projeto.repository.ProjetoRepository;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import com.br.ifba.apoio.empreendimentos.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class ProjetoService implements ProjetoIService {

    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Projeto criar(String criadorEmail, Projeto projeto) {
        Usuario criador = usuarioRepository.findByEmail(criadorEmail)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com email: " + criadorEmail));

        projeto.setCriador(criador);
        projeto.setValorArrecadado(0.0);
        projeto.setStatus("ativo"); // Define o status inicial como "ativo"

        return projetoRepository.save(projeto);
    }

    @Override
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + id));
    }

    @Override
    public List<Projeto> listarPorCriador(String criadorEmail) {
        Usuario criador = usuarioRepository.findByEmail(criadorEmail)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com email: " + criadorEmail));
        return projetoRepository.findByUsuario(criador);
    }

    @Override
    public List<Projeto> listarPorStatus(String status) {
        return projetoRepository.findByStatus(status);
    }

    @Override
    public Projeto atualizarProjeto(Long id, Projeto dadosAtualizados) {
        Projeto existente = buscarPorId(id);

        existente.setTitulo(dadosAtualizados.getTitulo());
        existente.setDescricao(dadosAtualizados.getDescricao());
        existente.setValorNecessario(dadosAtualizados.getValorNecessario());
        existente.setCategoria(dadosAtualizados.getCategoria());

        if (dadosAtualizados.getStatus() != null) {
            existente.setStatus(dadosAtualizados.getStatus());
        }

        return projetoRepository.save(existente);
    }

    /*
    public Projeto registrarApoioFinanceiro(Long id, Double valorApoio) {
       //ainda não implementado
    }*/

    @Override
    public void deletarProjeto(Long id) {
        Projeto projeto = buscarPorId(id);
        projetoRepository.delete(projeto);
    }
}