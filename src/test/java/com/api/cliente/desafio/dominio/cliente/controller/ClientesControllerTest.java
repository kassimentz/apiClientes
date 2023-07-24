package com.api.cliente.desafio.dominio.cliente.controller;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteRequestDTO;
import com.api.cliente.desafio.dominio.cliente.dto.ClienteResponseDTO;
import com.api.cliente.desafio.dominio.cliente.service.ClienteService;
import com.api.cliente.desafio.tests.Factory;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClientesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClienteService clienteService;

    @Test
    public void findByIdDeveRetornarUmClienteResponseDTOCasoIdExista() throws Exception {

        UUID id = UUID.fromString("879bfe49-d373-49ce-922c-bf4833543353");

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setRazaoSocial("Razao social");
        response.setTelefone("51-98765435");
        response.setEndereco("Endereco teste");
        response.setFaturamentoDeclarado(873.00);
        response.setDadosBancarios(Factory.createDadosBancariosList());
        response.setId(id);

        Mockito.when(clienteService.findById(id)).thenReturn(response);

        ResultActions result = mockMvc.perform(get("/produto/{id}", id).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());



    }

}