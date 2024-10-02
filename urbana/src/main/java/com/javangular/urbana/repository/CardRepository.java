package com.javangular.urbana.repository;

import java.util.List; // Importação correta da classe List do Java

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javangular.urbana.model.Cartao;

@Repository
public interface CardRepository extends JpaRepository<Cartao, Long> {

    // Método para buscar cartões pelo ID do usuário
    List<Cartao> findByUsuarioId(Long usuarioId);
}
