package com.api.cliente.desafio.dominio.cliente.dto;

import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import jakarta.validation.constraints.NotBlank;

public class DadosBancariosDTO {

    @NotBlank(message = "Agência ser informada")
    private String agencia;

    @NotBlank(message = "Conta ser informada")
    private String conta;
    @NotBlank(message = "Banco deve ser informado")
    private String banco;

    public DadosBancariosDTO() {
    }

    public DadosBancariosDTO(String agencia, String conta, String banco) {
        this.agencia = agencia;
        this.conta = conta;
        this.banco = banco;
    }

    public DadosBancariosDTO(DadosBancarios entidade) {
        this.agencia = entidade.getAgencia();
        this.conta = entidade.getConta();
        this.banco = entidade.getBanco();
    }

    public String getAgencia() {
        return agencia;
    }

    public DadosBancariosDTO setAgencia(String agencia) {
        this.agencia = agencia;
        return this;
    }

    public String getConta() {
        return conta;
    }

    public DadosBancariosDTO setConta(String conta) {
        this.conta = conta;
        return this;
    }

    public String getBanco() {
        return banco;
    }

    public DadosBancariosDTO setBanco(String banco) {
        this.banco = banco;
        return this;
    }
}
