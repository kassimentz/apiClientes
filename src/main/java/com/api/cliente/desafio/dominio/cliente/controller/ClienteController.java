package com.api.cliente.desafio.dominio.cliente.controller;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteDTO;
import com.api.cliente.desafio.dominio.cliente.dto.DadosBancariosDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import com.api.cliente.desafio.dominio.cliente.service.ClienteService;
import com.api.cliente.desafio.dominio.cliente.service.DadosBancariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    DadosBancariosService dadosBancariosService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var clientes = clienteService.findAll(pageRequest);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable UUID id) {
        var cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteDTO cliente) {
        var clienteSalvo = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@RequestBody ClienteDTO cliente, @PathVariable UUID id) {
        var clienteAtualizado = clienteService.update(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idCliente}/dados-bancarios")
    public ResponseEntity<DadosBancarios> adicionarDadosBancarios(@PathVariable UUID idCliente, @RequestBody DadosBancariosDTO dadosBancarios) {
        var dadosBancariosSalvo = dadosBancariosService.save(idCliente, dadosBancarios);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente/dadosBancarios}").buildAndExpand(idCliente).toUri();
        return ResponseEntity.created(uri).body(dadosBancariosSalvo);
    }

    @GetMapping("/{idCliente}/dados-bancarios")
    public ResponseEntity<List<DadosBancarios>> findAllDadosBancarios(@PathVariable UUID idCliente) {
        var dadosBancarios = dadosBancariosService.findAllDadosBancariosCliente(idCliente);
        return ResponseEntity.ok(dadosBancarios);
    }

    @GetMapping("/{idCliente}/dados-bancarios/{id}")
    public ResponseEntity<DadosBancarios> findDadosBancariosById(@PathVariable UUID id) {
        var dadosBancarios = dadosBancariosService.findById(id);
        return ResponseEntity.ok(dadosBancarios);
    }

    @DeleteMapping("/{idCliente}/dados-bancarios/{id}")
    public ResponseEntity deleteDadosBancarios(@PathVariable UUID id) {
        dadosBancariosService.deleteDadosBancarios(id);
        return ResponseEntity.noContent().build();
    }
}
