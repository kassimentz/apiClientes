package com.api.cliente.desafio.dominio.cliente.repository;

import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IClienteRepository extends JpaRepository<Cliente, UUID> {
}
