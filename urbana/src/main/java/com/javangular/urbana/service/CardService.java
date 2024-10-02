package com.javangular.urbana.service;

import com.javangular.urbana.dto.CartaoDTO;
import com.javangular.urbana.model.Cartao;
import com.javangular.urbana.repository.CardRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    // Listar todos os cartões
    public List<Cartao> listarTodosCartoes() {
        return cardRepository.findAll();
    }

    // Buscar um cartão pelo ID do cartão
    public Optional<Cartao> buscarCartaoPorId(Long id) {
        return cardRepository.findById(id);
    }

    // Buscar cartões pelo ID do usuário
    public List<Cartao> buscarCartoesPorUsuario(Long usuarioId) {
        return cardRepository.findByUsuarioId(usuarioId); // A consulta para buscar cartões do usuário
    }

    // Remover um cartão pelo ID
    public void removerCartao(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cartão não encontrado para o ID: " + id);
        }
    }

    // Ativar ou inativar um cartão (alterar o status)
     public Cartao alterarStatus(Long id, boolean status) {
        Cartao cartao = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cartão não encontrado"));

        // Altera o status do cartão
        cartao.setStatusCartao(status);
        
        // Salva a alteração
        return cardRepository.save(cartao);
    }

    // Atualiza um cartão existente
    public Optional<Cartao> atualizarCartao(Long id, CartaoDTO cartaoDTO) {
        Optional<Cartao> cartaoOptional = cardRepository.findById(id);
        if (cartaoOptional.isPresent()) {
            Cartao cartao = cartaoOptional.get();
            cartao.setNomeCartao(cartaoDTO.getNomeCartao());
            cartao.setTipoCartao(cartaoDTO.getTipoCartao());
            // Você pode querer manter o número do cartão inalterado, ou pode atualizá-lo se necessário
            // cartao.setNumeroCartao(cartaoDTO.getNumeroCartao());
            cartao.setStatusCartao(cartaoDTO.getStatusCartao());
            return Optional.of(cardRepository.save(cartao)); // Salva as alterações e retorna o cartão atualizado
        }
        return Optional.empty(); // Retorna vazio se o cartão não for encontrado
    }
}
