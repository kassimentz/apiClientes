package com.api.cliente.desafio.dominio.cliente.controller.exception;

import com.api.cliente.desafio.dominio.cliente.service.exception.DefaultError;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoForm extends DefaultError {
    private List<ValidacaoCampo> mensagens = new ArrayList<ValidacaoCampo>();

    public List<ValidacaoCampo> getMensagens() {
        return mensagens;
    }

    public void addMensagens(String campo, String mensagem) {
        mensagens.add(new ValidacaoCampo(campo, mensagem));
    }
}
