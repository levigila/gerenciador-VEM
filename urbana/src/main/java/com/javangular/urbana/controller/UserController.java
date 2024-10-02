package com.javangular.urbana.controller;

import com.javangular.urbana.dto.CartaoDTO;
import com.javangular.urbana.dto.UsuarioDTO;
import com.javangular.urbana.model.Cartao;
import com.javangular.urbana.model.Usuario;
import com.javangular.urbana.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Permite o domínio do Angular
@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    // Listar todos os usuários
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Usuario> listarTodos() {
        return userService.listarTodosUsuarios();
    }

    // Buscar um usuário pelo ID
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os detalhes de um usuário com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = userService.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar um novo usuário
    @PostMapping("/usuario")
    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário sem cartões.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "500", description = "Erro ao criar o usuário")
    })
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioCriado = userService.salvarUsuario(usuarioDTO);
        return usuarioCriado.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // Atualizar um usuário existente
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Atualiza os dados de um usuário existente com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioAtualizadoDTO) {
        Optional<Usuario> usuarioOptional = userService.atualizarUsuario(id, usuarioAtualizadoDTO);
        return usuarioOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remover um usuário
    @Operation(summary = "Remover usuário", description = "Remove um usuário do sistema com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id) {
        userService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Criar e adicionar novo cartão a um usuário
    @PostMapping("/{id}/cartao")
    @Operation(summary = "Criar e Adicionar novo cartão a um usuário", description = "Cria e adiciona um novo cartão a um usuário existente com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cartão adicionado com sucesso ao usuário"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Cartao> adicionarCartao(@PathVariable Long id, @RequestBody CartaoDTO cartaoDTO) {
        Optional<Cartao> cartaoOptional = userService.adicionarCartao(id, cartaoDTO);
        return cartaoOptional.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }
    


    // Remover um cartão de um usuário
    @Operation(summary = "Remover cartão de um usuário", description = "Remove um cartão de um usuário existente com base no ID fornecido e no ID do cartão.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cartão removido com sucesso do usuário"),
        @ApiResponse(responseCode = "404", description = "Usuário ou cartão não encontrado")
    })
    @DeleteMapping("/{id}/cartao/{cartaoId}")
    public ResponseEntity<Usuario> removerCartao(@PathVariable Long id, @PathVariable Long cartaoId) {
        Optional<Usuario> usuario = userService.removerCartao(id, cartaoId);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
