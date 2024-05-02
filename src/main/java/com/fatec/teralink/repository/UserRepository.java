package com.fatec.teralink.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.teralink.model.User;


// possibilita usar os metodos CRUD já feitos pelo SPRING
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @SuppressWarnings("null")
    Optional<User> findById(Long id);

    User findByUsername(String username);    
} 