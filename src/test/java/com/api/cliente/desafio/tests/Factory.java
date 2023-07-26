package com.api.cliente.desafio.tests;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteDTO;
import com.api.cliente.desafio.dominio.cliente.dto.DadosBancariosDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static DadosBancariosDTO createDadoBancarioDTO() {
        DadosBancarios dadosBancarios1 = new DadosBancarios.Builder()
                .agencia("001")
                .conta("0987-1")
                .banco("100")
                .build();
        return new DadosBancariosDTO(dadosBancarios1);
    }
    public static List<DadosBancarios> createDadosBancariosList() {
        DadosBancarios dadosBancarios1 = new DadosBancarios.Builder()
                .agencia("001")
                .conta("0987-1")
                .banco("100")
                .build();

        DadosBancarios dadosBancarios2 = new DadosBancarios.Builder()
                .agencia("001")
                .conta("0123-0")
                .banco("179")
                .build();

        List<DadosBancarios> dadosBancariosLista = new ArrayList<>();
        dadosBancariosLista.add(dadosBancarios1);
        dadosBancariosLista.add(dadosBancarios2);
        return dadosBancariosLista;
    }
    public static Cliente createCliente() {

        return new Cliente(
                "Cliente Um",
                "51-999999999",
                "Rua a, numero 2, apto 3",
                new BigDecimal(900.00)
        );
    }

    public static ClienteDTO createClienteRequestDTOComRazaoSocialAtualizada() {
        return new ClienteDTO(createCliente().setRazaoSocial("Atualizacao razao social do cliente"));
    }

}
