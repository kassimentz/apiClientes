package com.api.cliente.desafio.dominio.cliente.dto;

import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ClienteRequestDTO {

    @NotBlank(message = "Razão Social deve ser informado")
    private String razaoSocial;

    @NotBlank(message = "Telefone deve ser informado")
    private String telefone;

    @NotBlank(message = "Endereço deve ser informado")
    private String endereco;

//    @NotBlank(message = "Faturamento declarado deve ser informado")
//    @DecimalMin(value = "0.01", message = "O valor mínimo é 0.01")
//    @Digits(integer = 14, fraction = 2, message = "Formato inválido. Use até 14 dígitos inteiros e 2 decimais.")
    private double faturamentoDeclarado;

    private List<DadosBancarios> dadosBancarios;

    public ClienteRequestDTO() {
    }

    public ClienteRequestDTO(String razaoSocial, String telefone, String endereco, double faturamentoDeclarado, List<DadosBancarios> dadosBancarios) {
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.endereco = endereco;
        this.faturamentoDeclarado = faturamentoDeclarado;
        this.dadosBancarios = dadosBancarios;
    }

    public ClienteRequestDTO(Cliente entidade) {
        this.razaoSocial = entidade.getRazaoSocial();
        this.telefone = entidade.getTelefone();
        this.endereco = entidade.getEndereco();
        this.faturamentoDeclarado = entidade.getFaturamentoDeclarado().doubleValue();
        this.dadosBancarios = entidade.getDadosBancarios();
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public ClienteRequestDTO setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public ClienteRequestDTO setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public ClienteRequestDTO setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public double getFaturamentoDeclarado() {
        return faturamentoDeclarado;
    }

    public ClienteRequestDTO setFaturamentoDeclarado(double faturamentoDeclarado) {
        this.faturamentoDeclarado = faturamentoDeclarado;
        return this;
    }

    public List<DadosBancarios> getDadosBancarios() {
        return dadosBancarios;
    }

    public ClienteRequestDTO setDadosBancarios(List<DadosBancarios> dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
        return this;
    }
}
