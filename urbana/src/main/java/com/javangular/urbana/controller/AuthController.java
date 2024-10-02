package com.javangular.urbana.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javangular.urbana.model.Usuario;
import com.javangular.urbana.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        // Lógica para salvar o usuário no banco de dados e garantir que os dados estão corretos
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Usuario usuario) {
            // Lógica para autenticar o usuário
            String token = AuthService.authenticate(usuario.getEmail(), usuario.getSenha());
            if (token != null) {
                return ResponseEntity.ok(token); // Retorna o token em caso de sucesso
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
}
