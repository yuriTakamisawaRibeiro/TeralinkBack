package com.fatec.teralink.dto.UserDTO;



// DTO(Data Trasfer Object) 
// serve para dizer ao codigo quais atributos iremos usar para fazer as funções
// Update é o que iremos atualizar de dados do usuário no caso
public record UserUpdateDTO(Long id, String name, String email, String password) {
    
}
