package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Cliente;
import com.projetojpa.services.ClienteServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cliente", description = "API REST DE GERENCIAMENTO SE USUÁRIOS")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteServices clienteServices;
		
		@Autowired
		public ClienteController (ClienteServices clienteServices) {
			this.clienteServices = clienteServices;
		}
		@GetMapping("/{id}")
		@Operation(summary = "Localiza usuário por ID")
		public ResponseEntity <Cliente> buscaClienteIdControlId(@PathVariable Long id){
			Cliente cliente = clienteServices.buscaClienteId(id);
			if(cliente!= null) {
				return ResponseEntity.ok(cliente);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		@GetMapping("/")
		@Operation(summary = "Apresenta todos os usuários")
		public ResponseEntity<List<Cliente>> buscaTodasClienteControl() {
			List<Cliente> Cliente = clienteServices.buscaTodosCliente();

			return ResponseEntity.ok(Cliente);
		}
		@PostMapping("/")
		@Operation(summary = "Cadastra um usuário")
		public ResponseEntity<Cliente> salvaUsuarioControl(@RequestBody @Valid Cliente cliente){
			Cliente salvaCliente = clienteServices.salvaCliente(cliente);

			return ResponseEntity.status(HttpStatus.CREATED).body(salvaCliente);

		}
		@PutMapping ("/{id}")
		@Operation(summary = "altera as informações do id")
		public ResponseEntity<Cliente> alterarUsuario(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
			Cliente alterarCliente = clienteServices.alterarCliente(id,cliente);
			if (alterarCliente  != null) {
				return ResponseEntity.ok(alterarCliente);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		@DeleteMapping("/{id}")
		@Operation(summary = "Apagar o id selecionado")
		public ResponseEntity<String> apagaUsuarioControl(@PathVariable Long id) {
			boolean apagar = clienteServices.apagarCliente(id);
			if(apagar) {
				return ResponseEntity.ok().body("O produto foi excluído com sucesso");
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}

	}


