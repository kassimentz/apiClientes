package com.api.cliente.desafio.dominio.cliente.service;

import com.api.cliente.desafio.dominio.cliente.dto.DadosBancariosDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import com.api.cliente.desafio.dominio.cliente.repository.IDadoBancarioRepository;
import com.api.cliente.desafio.dominio.cliente.service.exception.ControllerNotFoundException;
import com.api.cliente.desafio.dominio.cliente.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DadosBancariosService {

    @Autowired
    private IDadoBancarioRepository repo;

    @Autowired
    private ClienteService clienteService;

    public List<DadosBancarios> findAllDadosBancariosCliente(UUID idCliente) {
        return repo.findByClienteId(idCliente);
    }

    public DadosBancarios findById(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Dados bancarios não encontrado"));
    }

    public DadosBancarios save(UUID idCliente, DadosBancariosDTO dadosBancariosDTO) {

        DadosBancarios dadosBancarios = new DadosBancarios();
        dadosBancarios.setAgencia(dadosBancariosDTO.getAgencia());
        dadosBancarios.setBanco(dadosBancariosDTO.getBanco());
        dadosBancarios.setConta(dadosBancariosDTO.getConta());

        Cliente cliente = clienteService.findById(idCliente);
        cliente.addDadosBancarios(dadosBancarios);
        dadosBancarios.setCliente(cliente);
        return repo.save(dadosBancarios);

    }

    public void deleteDadosBancarios(UUID id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entidade nao encontrada com o id: "+id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("VIolacao de integridade da base");
        }
    }


}
