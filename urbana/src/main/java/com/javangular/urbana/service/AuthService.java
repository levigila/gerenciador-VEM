package com.javangular.urbana.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    // Aqui você pode usar um repositório para verificar usuários em um banco de dados
    private static Map<String, String> users = new HashMap<>(); // Simulação de um repositório de usuários

    public AuthService() {
        // Usuários de exemplo, você pode remover isso e usar seu banco de dados
        users.put("user@example.com", "password123"); // email: password
    }

    public static String authenticate(String email, String password) {
        // Verifica se o usuário existe e se a senha está correta
        if (users.containsKey(email) && users.get(email).equals(password)) {
            // Aqui você pode gerar um token, por exemplo, usando JWT
            String token = "fake-jwt-token"; // Substitua por uma geração real de token
            return token;
        }
        return null; // Credenciais inválidas
    }
}
