package com.api.cliente.desafio.dominio.cliente.service;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteRequestDTO;
import com.api.cliente.desafio.dominio.cliente.dto.ClienteResponseDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.repository.IClienteRepository;
import com.api.cliente.desafio.dominio.cliente.service.exception.ControllerNotFoundException;
import com.api.cliente.desafio.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;

    @Mock
    private IClienteRepository repo;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private PageImpl<Cliente> page;


    private Cliente cliente;

    private ClienteRequestDTO clienteRazaoSocialAtualizada;


    @BeforeEach
    public void setUp(){
        idExistente = UUID.fromString("879bfe49-d373-49ce-922c-bf4833543353");
        idNaoExistente = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d550");
        pageRequest = PageRequest.of(0, 10);
        cliente = Factory.createCliente();
        clienteRazaoSocialAtualizada = Factory.createClienteRequestDTOComRazaoSocialAtualizada();
        page = new PageImpl<>(List.of(cliente));

        Mockito.when(repo.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(cliente));
        Mockito.when(repo.findAll((PageRequest)ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repo.findById(idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeClientesResponseDTO() {
        Page clienteResponseDTO = service.findAll(pageRequest);
        Assertions.assertNotNull(clienteResponseDTO);
    }

    @Test
    public void findByIdDeveRetornarUmClienteResponseDTOAoBuscarPorID() {
        ClienteResponseDTO clienteResponseDTO = service.findById(idExistente);
        Assertions.assertNotNull(clienteResponseDTO);
    }

    @Test
    public void findByIdDeveRetornarUmaExcecaoAoBuscarPorIdInexistente() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            service.findById(idNaoExistente);
        });
    }

    @Test
    public void updateDeveAtualizarARazaoSocial() {
        Assertions.assertEquals("Cliente Um", cliente.getRazaoSocial());
        service.update(idExistente, clienteRazaoSocialAtualizada);
        ClienteResponseDTO clienteAtualizado = service.findById(idExistente);
        Assertions.assertEquals("Atualizacao razao social do cliente", clienteAtualizado.getRazaoSocial());
    }
}