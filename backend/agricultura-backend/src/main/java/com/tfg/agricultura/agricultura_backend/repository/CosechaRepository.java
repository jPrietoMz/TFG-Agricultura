package com.tfg.agricultura.agricultura_backend.repository;

import com.tfg.agricultura.agricultura_backend.model.Cosecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CosechaRepository extends JpaRepository<Cosecha, Long> {
    // Retorna las cosechas de un usuario específico
    List<Cosecha> findByCultivoId(Long cultivoId);
}
