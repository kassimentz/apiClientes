package com.api.cliente.desafio.dominio.cliente.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="tb_dados_bancarios")
public class DadosBancarios {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String agencia;
    private String conta;

    private String banco;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public DadosBancarios(String agencia, String conta, String banco, Cliente cliente) {
        this.id = UUID.randomUUID();
        this.agencia = agencia;
        this.conta = conta;
        this.banco = banco;
        this.cliente = cliente;
    }

    public DadosBancarios() {
    }

    public UUID getId() {
        return id;
    }

    public DadosBancarios setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getAgencia() {
        return agencia;
    }

    public DadosBancarios setAgencia(String agencia) {
        this.agencia = agencia;
        return this;
    }

    public String getConta() {
        return conta;
    }

    public DadosBancarios setConta(String conta) {
        this.conta = conta;
        return this;
    }

    public String getBanco() {
        return banco;
    }

    public DadosBancarios setBanco(String banco) {
        this.banco = banco;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public DadosBancarios setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    @Override
    public String toString() {
        return "DadosBancarios{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", banco='" + banco + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosBancarios that = (DadosBancarios) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private String agencia;
        private String conta;
        private String banco;


        public Builder() {}

        public String getAgencia() {
            return agencia;
        }

        public Builder agencia(String agencia) {
            this.agencia = agencia;
            return this;
        }

        public String getConta() {
            return conta;
        }

        public Builder conta(String conta) {
            this.conta = conta;
            return this;
        }

        public String getBanco() {
            return banco;
        }

        public Builder banco(String banco) {
            this.banco = banco;
            return this;
        }

        public DadosBancarios build() {
            DadosBancarios dadosBancarios = new DadosBancarios();
            dadosBancarios.agencia = this.agencia;
            dadosBancarios.conta = this.conta;
            dadosBancarios.banco = this.banco;
            return dadosBancarios;
        }
    }
}
