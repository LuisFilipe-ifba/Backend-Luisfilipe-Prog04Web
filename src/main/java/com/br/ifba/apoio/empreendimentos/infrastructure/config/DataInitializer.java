package com.br.ifba.apoio.empreendimentos.infrastructure.config;

import com.br.ifba.apoio.empreendimentos.perfil.model.Perfil;
import com.br.ifba.apoio.empreendimentos.perfil.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Roda uma vez a cada subida da aplicação. Cria o perfil "APOIADOR" se
 * ele ainda não existir — é o perfil atribuído automaticamente a quem
 * se cadastra pelo site (ver UsuarioService.criarUsuario).
 *
 * Diferente do INSERT manual que fizemos por SQL para o "ADMIN", isso
 * usa o próprio PerfilRepository.save(), então o Hibernate cuida da
 * geração do id normalmente — sem risco de dessincronizar a sequência.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PerfilRepository perfilRepository;

    @Override
    public void run(String... args) {
        if (perfilRepository.findByDescricao("APOIADOR").isEmpty()) {
            Perfil apoiador = new Perfil();
            apoiador.setDescricao("APOIADOR");
            apoiador.setPermissoes(List.of("PROJETO_APOIAR", "MENSAGEM_CRIAR"));
            perfilRepository.save(apoiador);
        }
    }
}