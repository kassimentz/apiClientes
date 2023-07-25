package com.api.cliente.desafio.dominio.cliente.repository;

import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDadoBancarioRepository extends JpaRepository<DadosBancarios, UUID> {

    @Query("SELECT d FROM DadosBancarios d WHERE d.cliente.id = :clienteId")
    List<DadosBancarios> findByClienteId(UUID clienteId);
}
