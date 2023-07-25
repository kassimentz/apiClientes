package com.api.cliente.desafio.dominio.cliente.controller;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteRequestDTO;
import com.api.cliente.desafio.dominio.cliente.entity.Cliente;
import com.api.cliente.desafio.dominio.cliente.entity.DadosBancarios;
import com.api.cliente.desafio.dominio.cliente.service.ClienteService;
import com.api.cliente.desafio.dominio.cliente.service.DadosBancariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteRequestDTO cliente) {
        var clienteSalvo = clienteService.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@RequestBody ClienteRequestDTO cliente, @PathVariable UUID id) {
        var clienteAtualizado = clienteService.update(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idCliente}/dadosBancarios")
    public ResponseEntity<DadosBancarios> adicionarDadosBancarios(@PathVariable UUID idCliente, @RequestBody DadosBancarios dadosBancarios) {
        var dadosBancariosSalvo = dadosBancariosService.save(idCliente, dadosBancarios);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCliente/dadosBancarios}").buildAndExpand(idCliente).toUri();
        return ResponseEntity.created(uri).body(dadosBancariosSalvo);
    }

    @GetMapping("/{idCliente}/dadosBancarios")
    public ResponseEntity<List<DadosBancarios>> findDadosBancarios(@PathVariable UUID idCliente) {
        var dadosBancarios = dadosBancariosService.findDadosBancariosCliente(idCliente);
        return ResponseEntity.ok(dadosBancarios);
    }

    @GetMapping("/dadosBancarios/{id}")
    public ResponseEntity<DadosBancarios> findDadosBancariosById(@PathVariable UUID id) {
        var dadosBancarios = dadosBancariosService.findById(id);
        return ResponseEntity.ok(dadosBancarios);
    }

    @DeleteMapping("/dadosBancarios/{id}")
    public ResponseEntity deleteDadosBancarios(@PathVariable UUID id) {
        dadosBancariosService.deleteDadosBancarios(id);
        return ResponseEntity.noContent().build();
    }
}
