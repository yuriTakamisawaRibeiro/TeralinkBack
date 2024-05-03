package com.fatec.teralink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fatec.teralink.dto.UserDTO.UserRequestDTO;
import com.fatec.teralink.dto.UserDTO.UserResponseDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTOReturn;
import com.fatec.teralink.model.User;
import com.fatec.teralink.repository.UserRepository;

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

   

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkUserCredentials(String email, String password) {
        User user = findUserByEmail(email);
        if(user != null && password.matches(password)) {
                return true;
            }
        
        return false;
    }
}


