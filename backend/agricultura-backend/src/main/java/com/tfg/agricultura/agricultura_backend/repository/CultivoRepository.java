package com.tfg.agricultura.agricultura_backend.repository;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
    // Cambiar findByUsuarioId por findByUsuario_Id para acceder a la relación correctamente
    List<Cultivo> findByUsuario_Id(Long usuarioId);

    Cultivo findByNombre(String nombre);

    boolean existsByNombre(String nombre); // Agregar este método


}


