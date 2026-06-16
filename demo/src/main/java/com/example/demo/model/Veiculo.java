package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_veiculo;
    
    private String marca;
    private String modelo;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // --- GETTERS E SETTERS ---
    
    public Long getId_veiculo() { 
        return id_veiculo; 
    }
    
    public void setId_veiculo(Long id_veiculo) { 
        this.id_veiculo = id_veiculo; 
    }

    public String getMarca() { 
        return marca; 
    }
    
    public void setMarca(String marca) { 
        this.marca = marca; 
    }

    public String getModelo() { 
        return modelo; 
    }
    
    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    }

    public String getPlaca() { 
        return placa; 
    }
    
    public void setPlaca(String placa) { 
        this.placa = placa; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }
    
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }
}