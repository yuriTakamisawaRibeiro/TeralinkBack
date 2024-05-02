package com.fatec.teralink.dto.UserDTO;

import com.fatec.teralink.model.User;

// DTO(Data Trasfer Object) 
// serve para dizer ao codigo quais atributos iremos usar para fazer as funções
// Response é o que iremos mostrar ao usuário no caso(por enquanto para testes iremos mostrar a senha, depois ela nem estará disponivel para ver por segurança).
public record UserResponseDTO(Long id, String name, String email, String cpf, String password) {
    
    // construtores
    public UserResponseDTO(User user) {

        this(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getCpf(),
            user.getPassword()
        );
    }
}
