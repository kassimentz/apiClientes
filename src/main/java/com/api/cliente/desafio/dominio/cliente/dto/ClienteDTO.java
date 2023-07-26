package com.api.cliente.desafio.dominio.cliente.dto;

import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import jakarta.validation.constraints.*;

import java.util.List;

public class ClienteDTO {

    @NotBlank(message = "Razão Social deve ser informado")
    private String razaoSocial;

    @NotBlank(message = "Telefone deve ser informado")
    private String telefone;

    @NotBlank(message = "Endereço deve ser informado")
    private String endereco;

    @NotNull(message = "Faturamento declarado deve ser informado")
    @DecimalMin(value = "0.01", message = "O valor mínimo é 0.01")
    @Digits(integer = 14, fraction = 2, message = "Formato inválido. Use até 14 dígitos inteiros e 2 decimais.")
    private double faturamentoDeclarado;


    public ClienteDTO() {
    }

    public ClienteDTO(String razaoSocial, String telefone, String endereco, double faturamentoDeclarado, List<DadosBancarios> dadosBancarios) {
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.endereco = endereco;
        this.faturamentoDeclarado = faturamentoDeclarado;
    }

    public ClienteDTO(Cliente entidade) {
        this.razaoSocial = entidade.getRazaoSocial();
        this.telefone = entidade.getTelefone();
        this.endereco = entidade.getEndereco();
        this.faturamentoDeclarado = entidade.getFaturamentoDeclarado().doubleValue();
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public ClienteDTO setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public ClienteDTO setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public ClienteDTO setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public double getFaturamentoDeclarado() {
        return faturamentoDeclarado;
    }

    public ClienteDTO setFaturamentoDeclarado(double faturamentoDeclarado) {
        this.faturamentoDeclarado = faturamentoDeclarado;
        return this;
    }
}
