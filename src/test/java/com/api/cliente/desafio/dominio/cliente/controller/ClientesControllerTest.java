package com.api.cliente.desafio.dominio.cliente.controller;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteDTO;
import com.api.cliente.desafio.dominio.cliente.dto.DadosBancariosDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.service.ClienteService;
import com.api.cliente.desafio.dominio.cliente.service.DadosBancariosService;
import com.api.cliente.desafio.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(ClienteController.class)
class ClientesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClienteService clienteService;

    @MockBean
    DadosBancariosService dadosBancariosService;

    UUID clienteId;

    @BeforeEach
    public void setUp() {
        clienteId = UUID.fromString("879bfe49-d373-49ce-922c-bf4833543353");
    }
    @Test
    public void findByIdDeveRetornarUmClienteCasoIdExista() throws Exception {

        Cliente response = new Cliente();
        response.setRazaoSocial("Razao social");
        response.setTelefone("51-98765435");
        response.setEndereco("Endereco teste");
        response.setFaturamentoDeclarado(new BigDecimal(873.00));
        response.setId(clienteId);

        Mockito.when(clienteService.findById(clienteId)).thenReturn(response);

        ResultActions result = mockMvc.perform(get("/clientes/{id}", clienteId).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

    }

    @Test
    public void testDeveInserirUmNovoCliente() throws Exception {
        Cliente cliente = Factory.createCliente();

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeveAtualizarUmCliente() throws Exception {

        ClienteDTO cliente = Factory.createClienteRequestDTOComRazaoSocialAtualizada();

        mockMvc.perform(put("/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeveExcluirCliente() throws Exception {

        mockMvc.perform(delete("/clientes/{id}", clienteId))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testDeveInserirUmDadoBancarioParaUmCliente() throws Exception {
        DadosBancariosDTO dadosBancariosDTO = Factory.createDadoBancarioDTO();

        mockMvc.perform(post("/clientes/{idCliente}/dados-bancarios", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dadosBancariosDTO)))
                .andExpect(status().isCreated());
    }


    private static String asJsonString(final Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}