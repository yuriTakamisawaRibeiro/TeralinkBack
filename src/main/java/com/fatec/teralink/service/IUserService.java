package com.fatec.teralink.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fatec.teralink.dto.UserDTO.UserRequestDTO;
import com.fatec.teralink.dto.UserDTO.UserResponseDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTOReturn;


// interface, que é contrato dos metodos que irão ser implementados pela classe Service, e depois irão ser consumidos pela classe controller, sem precisar saber da lógica por tras
public interface IUserService {
    
    void saveUser(UserRequestDTO data);

    List<UserResponseDTO> getAllUsers();

    ResponseEntity<Void> deleteUser(Long id);

    ResponseEntity<UserUpdateDTOReturn> updateUser(Long id, UserUpdateDTO data);

}
