package com.example.demo.repository;

import com.example.demo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    @Query("SELECT v FROM Veiculo v WHERE v.usuario.id_usuario = :idUsuario")
    List<Veiculo> buscarPorIdDono(@Param("idUsuario") Long idUsuario);
}