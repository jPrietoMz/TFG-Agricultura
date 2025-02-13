package com.tfg.agricultura.agricultura_backend.repository;

import com.tfg.agricultura.agricultura_backend.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    List<Tratamiento> findByCultivoId(Long cultivoId);
}
