package com.javangular.urbana.controller;

import com.javangular.urbana.dto.CartaoDTO;
import com.javangular.urbana.model.Cartao;
import com.javangular.urbana.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Permite o domínio do Angular
@RestController
@RequestMapping("/cartoes")

public class CardController {

    @Autowired
    private CardService cardService;

    // Listar todos os cartões
    @Operation(summary = "Listar todos os cartões", description = "Retorna uma lista de todos os cartões cadastrados.", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cartões retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Cartao> listarTodos() {
        return cardService.listarTodosCartoes();
    }

    // Buscar um cartão pelo ID do cartão
    @Operation(summary = "Buscar cartão por ID", description = "Retorna os detalhes de um cartão com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cartão encontrado"),
        @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cartao> buscarPorId(@PathVariable Long id) {
        Optional<Cartao> cartao = cardService.buscarCartaoPorId(id);
        return cartao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar cartões pelo ID do usuário
    @Operation(summary = "Buscar cartões por ID de usuário", description = "Retorna uma lista de cartões associados ao usuário com o ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cartões encontrada"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado ou não possui cartões")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Cartao>> buscarCartoesPorUsuario(@PathVariable Long usuarioId) {
        List<Cartao> cartoes = cardService.buscarCartoesPorUsuario(usuarioId);
        if (cartoes.isEmpty()) {
            return ResponseEntity.notFound().build(); // Se não encontrar cartões, retorna 404
        }
        return ResponseEntity.ok(cartoes); // Retorna a lista de cartões
    }

    // Remover um cartão pelo ID do cartão
    @Operation(summary = "Remover cartão", description = "Remove um cartão do sistema com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cartão removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCartao(@PathVariable Long id) {
        cardService.removerCartao(id);
        return ResponseEntity.noContent().build();
    }
    // Altera Status do Cartão (Ativa/Desativa)
    @Operation(summary = "Alterar status do cartão", description = "Ativa ou inativa um cartão com base no ID fornecido e no status desejado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status do cartão alterado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cartão não encontrado"),
        @ApiResponse(responseCode = "400", description = "Pedido inválido ou erro ao alterar o status")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> alterarStatus(@PathVariable Long id, @RequestParam boolean status) {
        try {
            Cartao cartao = cardService.alterarStatus(id, status);
            return ResponseEntity.ok(cartao); // Sucesso
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao alterar o status do cartão.");
        }
    }

    // Endpoint para atualizar um cartão
    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizarCartao(@PathVariable Long id, @RequestBody CartaoDTO cartaoDTO) {
        Optional<Cartao> cartaoAtualizado = cardService.atualizarCartao(id, cartaoDTO);
        return cartaoAtualizado
            .map(ResponseEntity::ok) // Retorna 200 OK se o cartão foi atualizado
            .orElseGet(() -> ResponseEntity.notFound().build()); // Retorna 404 NOT FOUND se não foi encontrado
    }
}
