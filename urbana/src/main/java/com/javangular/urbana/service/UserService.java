package com.javangular.urbana.service;

import com.javangular.urbana.dto.CartaoDTO;
import com.javangular.urbana.dto.UsuarioDTO;
import com.javangular.urbana.model.Cartao;
import com.javangular.urbana.model.Usuario;
import com.javangular.urbana.repository.UserRepository;
import com.javangular.urbana.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    // Listar todos os usuários
    public List<Usuario> listarTodosUsuarios() {
        return userRepository.findAll();
    }

    // Buscar um usuário por ID
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return userRepository.findById(id);
    }

// Método para converter UsuarioDTO em Usuario
private Usuario converterDtoParaEntidade(UsuarioDTO usuarioDTO) {
    Usuario usuario = new Usuario();
    usuario.setNome(usuarioDTO.getNome());
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setSenha(usuarioDTO.getSenha());
    return usuario;
}
// Método para salvar um novo usuário sem cartões
public Optional<Usuario> salvarUsuario(UsuarioDTO usuarioDTO) {
    try {
        // Converte o DTO para a entidade Usuario
        Usuario usuario = converterDtoParaEntidade(usuarioDTO);
        
        // Inicializa a lista de cartões vazia, caso o usuário não tenha cartões
        if (usuario.getCartoes() == null) {
            usuario.setCartoes(new ArrayList<>());
        }
        
        // Salva o usuário no repositório
        Usuario usuarioSalvo = userRepository.save(usuario);
        
        return Optional.ofNullable(usuarioSalvo);
    } catch (Exception e) {
        // Log de erro, se houver falha
        System.err.println("Erro ao salvar o usuário: " + e.getMessage());
        return Optional.empty();
    }
}


    // Remover um usuário por ID
    public void removerUsuario(Long id) {
        userRepository.deleteById(id);
    }

    // Atualizar Usuário por ID
    public Optional<Usuario> atualizarUsuario(Long id, UsuarioDTO usuarioAtualizadoDTO) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNome(usuarioAtualizadoDTO.getNome());
            usuarioExistente.setEmail(usuarioAtualizadoDTO.getEmail());
            // Outros campos de usuário conforme necessário

            userRepository.save(usuarioExistente);
            return Optional.of(usuarioExistente);
        }
        return Optional.empty();
    }


    // Adiciona/Cria um novo cartão associado ao usuário
public Optional<Cartao> adicionarCartao(Long id, CartaoDTO cartaoDTO) {
    Optional<Usuario> usuarioOptional = userRepository.findById(id);
    if (usuarioOptional.isPresent()) {
        Usuario usuario = usuarioOptional.get();
        Cartao cartao = new Cartao();
        
        cartao.setNomeCartao(cartaoDTO.getNomeCartao());
        
        // Gerar número de cartão automaticamente
        cartao.setNumeroCartao(gerarNumeroCartaoUnico());
        
        cartao.setTipoCartao(cartaoDTO.getTipoCartao());
        
        // Status definido como ativo por padrão
        cartao.setStatusCartao(true);
        
        cartao.setUsuario(usuario);
        
        Cartao cartaoAdicionado = cardRepository.save(cartao);
        return Optional.of(cartaoAdicionado); // Retorna o cartão adicionado
    }
    return Optional.empty();
}

// Método auxiliar para gerar um número de cartão único
private String gerarNumeroCartaoUnico() {
    // Pode usar qualquer lógica aqui, por exemplo, UUID ou um gerador customizado
    return UUID.randomUUID().toString(); // Exemplo simples usando UUID
}




    // Remover um cartão de um usuário
    public Optional<Usuario> removerCartao(Long usuarioId, Long cartaoId) {
        Optional<Usuario> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            // Remover o cartão da lista de cartões do usuário
            u.getCartoes().removeIf(cartao -> cartao.getId().equals(cartaoId));
            userRepository.save(u); // Salva o usuário atualizado
            return Optional.of(u);
        } else {
            return Optional.empty();
        }
    }
}
