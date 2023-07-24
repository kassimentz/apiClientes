package com.api.cliente.desafio.dominio.cliente.dto;

import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ClienteResponseDTO {

    private UUID id;

    private String razaoSocial;

    private String telefone;

    private String endereco;

    private LocalDateTime dataDeCadastro;

    private double faturamentoDeclarado;

    private List<DadosBancarios> dadosBancarios;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(UUID id, String razaoSocial, String telefone, String endereco, LocalDateTime dataDeCadastro, double faturamentoDeclarado, List<DadosBancarios> dadosBancarios) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataDeCadastro = dataDeCadastro;
        this.faturamentoDeclarado = faturamentoDeclarado;
        this.dadosBancarios = dadosBancarios;
    }

    public ClienteResponseDTO(Cliente entidade) {
        this.id = entidade.getId();
        this.razaoSocial = entidade.getRazaoSocial();
        this.telefone = entidade.getTelefone();
        this.endereco = entidade.getEndereco();
        this.dataDeCadastro = entidade.getDataCadastro();
        this.faturamentoDeclarado = entidade.getFaturamentoDeclarado().doubleValue();
        this.dadosBancarios = entidade.getDadosBancarios();
    }

    public UUID getId() {
        return id;
    }

    public ClienteResponseDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public ClienteResponseDTO setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public ClienteResponseDTO setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public ClienteResponseDTO setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public LocalDateTime getDataDeCadastro() {
        return dataDeCadastro;
    }

    public ClienteResponseDTO setDataDeCadastro(LocalDateTime dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
        return this;
    }

    public double getFaturamentoDeclarado() {
        return faturamentoDeclarado;
    }

    public ClienteResponseDTO setFaturamentoDeclarado(double faturamentoDeclarado) {
        this.faturamentoDeclarado = faturamentoDeclarado;
        return this;
    }

    public List<DadosBancarios> getDadosBancarios() {
        return dadosBancarios;
    }

    public ClienteResponseDTO setDadosBancarios(List<DadosBancarios> dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
        return this;
    }
}
