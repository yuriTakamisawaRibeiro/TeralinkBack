package com.fatec.teralink.dto.UserDTO;

// DTO(Data Trasfer Object) 
// serve para dizer ao codigo quais atributos iremos usar para fazer as funções
// Request é o que iremos pedir do usuário no caso
public record UserRequestDTO(
        String username,
        String cpf,
        String email,
        String password) {

}