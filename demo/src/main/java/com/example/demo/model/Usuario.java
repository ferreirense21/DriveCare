package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    
    private String nome;
    private String email;
    private String senha;
    private String cpf_cnpj;

    // --- GETTERS E SETTERS ---
    
    public Long getId_usuario() { 
        return id_usuario; 
    }
    
    public void setId_usuario(Long id_usuario) { 
        this.id_usuario = id_usuario; 
    }

    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getEmail() { 
        return email; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getSenha() { 
        return senha; 
    }
    
    public void setSenha(String senha) { 
        this.senha = senha; 
    }

    public String getCpf_cnpj() { 
        return cpf_cnpj; 
    }
    
    public void setCpf_cnpj(String cpf_cnpj) { 
        this.cpf_cnpj = cpf_cnpj; 
    }
}