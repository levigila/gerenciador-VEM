package com.javangular.urbana.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javangular.urbana.model.Cartao;
import com.javangular.urbana.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    
    // Define o método para buscar um usuário pelo email
    Optional<Usuario> findByEmail(String email);

    Cartao save(Cartao cartao);


  
}
