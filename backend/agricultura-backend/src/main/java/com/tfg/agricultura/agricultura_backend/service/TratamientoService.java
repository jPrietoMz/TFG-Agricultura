package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.Tratamiento;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import com.tfg.agricultura.agricultura_backend.repository.TratamientoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final CultivoRepository cultivoRepository;

    public TratamientoService(TratamientoRepository tratamientoRepository, CultivoRepository cultivoRepository) {
        this.tratamientoRepository = tratamientoRepository;
        this.cultivoRepository = cultivoRepository;
    }

    // 🔹 Listar todos los tratamientos de un cultivo
    public List<Tratamiento> listarTratamientosPorCultivo(Long cultivoId) {
        return tratamientoRepository.findByCultivoId(cultivoId);
    }

    // 🔹 Crear un nuevo tratamiento asociado a un cultivo
    public Tratamiento crearTratamiento(Long cultivoId, Tratamiento tratamiento) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));
        tratamiento.setCultivo(cultivo);
        return tratamientoRepository.save(tratamiento);
    }

    // 🔹 Obtener un tratamiento por ID
    public Tratamiento obtenerTratamiento(Long tratamientoId) {
        return tratamientoRepository.findById(tratamientoId)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
    }

    // 🔹 Eliminar un tratamiento
    public void eliminarTratamiento(Long tratamientoId) {
        tratamientoRepository.deleteById(tratamientoId);
    }
}
