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
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final UsuarioRepository usuarioRepository;

    public Projeto criarProjeto(Long criadorId, Projeto projeto) {
        Usuario criador = usuarioRepository.findById(criadorId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + criadorId));

        projeto.setCriador(criador);
        projeto.setValorArrecadado(0.0);
        projeto.setStatus("ativo"); // Define o status inicial como "ativo"

        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + id));
    }

    public List<Projeto> listarPorCriador(Long criadorId) {
        return projetoRepository.findByCriadorId(criadorId);
    }

    public List<Projeto> listarPorStatus(String status) {
        return projetoRepository.findByStatus(status);
    }

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

    public Projeto registrarApoioFinanceiro(Long id, Double valorApoio) {
        Projeto projeto = buscarPorId(id);

        double novoValorArrecadado = projeto.getValorArrecadado() + valorApoio;
        projeto.setValorArrecadado(novoValorArrecadado);

        if (novoValorArrecadado >= projeto.getValorNecessario()) {
            projeto.setStatus("concluido");
        }

        return projetoRepository.save(projeto);
    }

    public void deletarProjeto(Long id) {
        Projeto projeto = buscarPorId(id);
        projetoRepository.delete(projeto);
    }
}