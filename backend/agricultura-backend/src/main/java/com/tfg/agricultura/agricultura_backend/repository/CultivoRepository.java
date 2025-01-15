package com.tfg.agricultura.agricultura_backend.repository;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
    // Método para obtener cultivos por usuario
    List<Cultivo> findByUsuarioId(Long usuarioId);

    // Método para buscar cultivos por nombre
    Cultivo findByNombre(String nombre);
}
