package com.api.cliente.desafio.dominio.cliente.service;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteRequestDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.repository.IClienteRepository;
import com.api.cliente.desafio.dominio.cliente.service.exception.ControllerNotFoundException;
import com.api.cliente.desafio.dominio.cliente.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repo;

    public Page<Cliente> findAll(PageRequest pagina) {
        var clientes = repo.findAll(pagina);
        return clientes;
    }

    public Cliente findById(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Cliente não encontrado"));
    }

    public Cliente save (ClienteRequestDTO requestDTO) {
        Cliente cliente = new Cliente();
        cliente.setRazaoSocial(requestDTO.getRazaoSocial());
        cliente.setTelefone(requestDTO.getTelefone());
        cliente.setEndereco(requestDTO.getEndereco());
        cliente.setFaturamentoDeclarado(BigDecimal.valueOf(requestDTO.getFaturamentoDeclarado()));
        cliente.setDadosBancarios(requestDTO.getDadosBancarios());
        return repo.save(cliente);
    }

    public Cliente update(UUID id, ClienteRequestDTO requestDTO) {
        try {
            Cliente clienteEncontrado = repo.getOne(id);
            clienteEncontrado.setRazaoSocial(requestDTO.getRazaoSocial());
            clienteEncontrado.setTelefone(requestDTO.getTelefone());
            clienteEncontrado.setEndereco(requestDTO.getEndereco());
            clienteEncontrado.setFaturamentoDeclarado(BigDecimal.valueOf(requestDTO.getFaturamentoDeclarado()));
            clienteEncontrado.setDadosBancarios(requestDTO.getDadosBancarios());
            return repo.save(clienteEncontrado);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Cliente nao encontrado, id: " + id);
        }
    }

    public void delete(UUID id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entidade nao encontrada com o id: "+id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("VIolacao de integridade da base");
        }

    }
}
