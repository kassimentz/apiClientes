package com.api.cliente.desafio.dominio.cliente.controller;

import com.api.cliente.desafio.dominio.cliente.dto.ClienteRequestDTO;
import com.api.cliente.desafio.dominio.cliente.dto.ClienteResponseDTO;
import com.api.cliente.desafio.dominio.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var clientes = service.findAll(pageRequest);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable UUID id) {
        var cliente = service.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@Valid @RequestBody ClienteRequestDTO cliente) {
        var clienteSalvo = service.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@RequestBody ClienteRequestDTO cliente, @PathVariable UUID id) {
        var clienteAtualizado = service.update(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //TODO adicionar endpoint para adicionar contas para esse cliente
}
