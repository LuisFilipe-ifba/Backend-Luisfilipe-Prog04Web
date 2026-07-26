package com.br.ifba.apoio.empreendimentos.infrastructure.exeption;

/**
 * Lançada quando alguém tenta se cadastrar com um email que já existe.
 * Sem isso, a violação da constraint "unique" do banco vira um erro 500
 * cru (DataIntegrityViolationException), que não é uma mensagem útil
 * para mostrar no formulário de cadastro.
 */
public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String email) {
        super("Já existe uma conta cadastrada com o email: " + email);
    }
}