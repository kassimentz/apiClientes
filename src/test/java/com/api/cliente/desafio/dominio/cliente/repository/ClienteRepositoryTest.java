package com.api.cliente.desafio.dominio.cliente.repository;

import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.service.exception.ControllerNotFoundException;
import com.api.cliente.desafio.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private IClienteRepository clienteRepository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private long countTotalClientes;
    private String razaoSocialAtualizada;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = UUID.fromString("879bfe49-d373-49ce-922c-bf4833543353");
        idNaoExistente = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d550");
        pageRequest = PageRequest.of(0, 10);
        countTotalClientes = 3L;
        razaoSocialAtualizada = "Atualizacao Nome da razao social";
    }

    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados() {
        Page<Cliente> clientes = clienteRepository.findAll(this.pageRequest);
        Assertions.assertEquals(countTotalClientes, clientes.getTotalElements());
    }

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        Optional<Cliente> result = clienteRepository.findById(idExistente);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            clienteRepository.findById(idNaoExistente).orElseThrow(() -> new ControllerNotFoundException("Cliente não encontrado"));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {

        Cliente cliente = Factory.createCliente();
        cliente.setId(null);
        var clienteSalvo = clienteRepository.save(cliente);

        Assertions.assertNotNull(clienteSalvo.getId());
    }
    @Test
    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull() {
        Cliente cliente = Factory.createCliente();
        cliente.setId(idExistente);
        cliente.setRazaoSocial(razaoSocialAtualizada);

        var clienteSalvo = clienteRepository.save(cliente);

        Assertions.assertEquals(razaoSocialAtualizada, clienteSalvo.getRazaoSocial());
    }

    @Test
    public void deleteDeveDeletarObjetoCasoExista() {
        clienteRepository.deleteById(idExistente);
        Optional<Cliente> result = clienteRepository.findById(idExistente);
        Assertions.assertFalse(result.isPresent());
    }

}