package com.fatec.teralink.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.br.CPF;

import com.fatec.teralink.dto.UserDTO.UserRequestDTO;
import com.fatec.teralink.dto.UserDTO.UserUpdateDTO;

// parte que tem os atributos das entidades, as constraints já são colocadas direto no banco de dados nessa sessão, pois todas as classes que usarão elas irão ter as mesmas restrições, sem ter que modificar os metodos.

@Entity(name= "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "name", nullable = false)
    private String name;

    @Email
    @Column(name= "email", nullable = false)
    private String email;

    @CPF
    @Column(name= "CPF", nullable = false)
    private String cpf;


    @Column(name= "password", nullable = false)
    private String password;

    // validador de dados chamado "data"
    public User(@Valid UserRequestDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.password = data.password();
    }


    public void updateUser(UserUpdateDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.password() != null) {
            this.password = data.password();
        }
    }
}
