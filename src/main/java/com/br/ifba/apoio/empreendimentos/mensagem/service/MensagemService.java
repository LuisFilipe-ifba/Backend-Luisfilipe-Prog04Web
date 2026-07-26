package com.br.ifba.apoio.empreendimentos.mensagem.service;

import com.br.ifba.apoio.empreendimentos.mensagem.model.Mensagem;
import com.br.ifba.apoio.empreendimentos.mensagem.repository.MensagemRepository;
import com.br.ifba.apoio.empreendimentos.projeto.model.Projeto;
import com.br.ifba.apoio.empreendimentos.projeto.repository.ProjetoRepository;
import com.br.ifba.apoio.empreendimentos.usuario.model.Usuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final ProjetoRepository projetoRepository;

    public Mensagem criarMensagem(Usuario autor, Long projetoId, String texto) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com id: " + projetoId));

        Mensagem mensagem = new Mensagem();
        mensagem.setUsuario(autor);
        mensagem.setProjeto(projeto);
        mensagem.setMensagem(texto);

        return mensagemRepository.save(mensagem);
    }

    public List<Mensagem> listarPorProjeto(Long projetoId) {
        return mensagemRepository.findByProjeto_IdOrderByDataAsc(projetoId);
    }

    public List<Mensagem> listarPorUsuario(Long usuarioId) {
        return mensagemRepository.findByUsuario_IdOrderByDataDesc(usuarioId);
    }

    public void deletarMensagem(Long id, Usuario usuarioAutenticado) {
        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada com id: " + id));

        if (!mensagem.getUsuario().getId().equals(usuarioAutenticado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para apagar esta mensagem.");
        }

        mensagemRepository.delete(mensagem);
    }
}