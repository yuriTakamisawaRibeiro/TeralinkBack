package com.fatec.teralink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatec.teralink.dto.LoginDTO.LoginRequestDTO;
import com.fatec.teralink.dto.UserDTO.UserRequestDTO;
import com.fatec.teralink.dto.UserDTO.UserResponseDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTOReturn;
import com.fatec.teralink.model.User;
import com.fatec.teralink.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import jakarta.transaction.Transactional;

@Service
public class UserService implements IUserService {
   
    // Injeção de dependencia, permite usar atributos e metodos de outras classes
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //------------------------------------------------------

    // metodos CRUD de usuário(irei ver melhor sobre as permissões e diferenças entre paciente e terapeuta)

    @Transactional
    public void saveUser(UserRequestDTO data) {
        User userData = new User(data);
        userRepository.save(userData);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new)
                .toList();
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity<UserUpdateDTOReturn> updateUser(Long id, UserUpdateDTO data) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.updateUser(data);
            userRepository.save(user);
            return ResponseEntity.ok(new UserUpdateDTOReturn(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<UserResponseDTO> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(new UserResponseDTO(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Login

   /*  @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public ResponseEntity<UserResponseDTO> loginUser(LoginRequestDTO data) {
        User user = userRepository.findByUsername((String) data.getUsername());
    
        if (user != null && passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            // usuário encontrado e senha correta
            return ResponseEntity.ok(new UserResponseDTO(user));
        } else {
            // usuário não encontrado ou senha incorreta
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }*/
}

