package com.api.cliente.desafio.dominio.cliente.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Immutable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String razaoSocial;
    private String telefone;
    private String endereco;
    private LocalDateTime dataCadastro;

    private BigDecimal faturamentoDeclarado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DadosBancarios> dadosBancarios = new ArrayList<>();

    public Cliente(String razaoSocial, String telefone, String endereco, BigDecimal faturamentoDeclarado, List<DadosBancarios> dadosBancarios) {
        this.id = UUID.randomUUID();
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = LocalDateTime.now();
        this.faturamentoDeclarado = faturamentoDeclarado;
        this.dadosBancarios = dadosBancarios;
    }

    public Cliente(String razaoSocial, String telefone, String endereco, BigDecimal faturamentoDeclarado) {
        this.id = UUID.randomUUID();
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = LocalDateTime.now();
        this.faturamentoDeclarado = faturamentoDeclarado;
    }

    public Cliente() {
        this.id = UUID.randomUUID();
        this.dataCadastro = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Cliente setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public Cliente setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Cliente setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Cliente setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public BigDecimal getFaturamentoDeclarado() {
        return faturamentoDeclarado;
    }

    public Cliente setFaturamentoDeclarado(BigDecimal faturamentoDeclarado) {
        this.faturamentoDeclarado = faturamentoDeclarado;
        return this;
    }

    public List<DadosBancarios> getDadosBancarios() {
        return Collections.unmodifiableList(this.dadosBancarios);
    }

    public Cliente addDadosBancarios(DadosBancarios dadosBancarios) {
        this.dadosBancarios.add(dadosBancarios);
        return this;
    }

    public Cliente setDadosBancarios(List<DadosBancarios> dadosBancariosList) {
        this.dadosBancarios = dadosBancariosList;
        return this;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", faturamentoDeclarado='" + faturamentoDeclarado + '\'' +
                ", dadosBancarios=" + dadosBancarios +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
