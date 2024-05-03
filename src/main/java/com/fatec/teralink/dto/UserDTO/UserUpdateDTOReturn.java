package com.fatec.teralink.dto.UserDTO;

import com.fatec.teralink.model.User;

public record UserUpdateDTOReturn(Long id, String name, String email, String password) {
    
    public UserUpdateDTOReturn(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
            );
    }
}
