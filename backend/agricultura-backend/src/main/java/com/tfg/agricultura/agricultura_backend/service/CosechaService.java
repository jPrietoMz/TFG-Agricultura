package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.model.Cosecha;
import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.repository.CosechaRepository;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosechaService {

    private final CosechaRepository cosechaRepository;
    private final CultivoRepository cultivoRepository;

    public CosechaService(CosechaRepository cosechaRepository, CultivoRepository cultivoRepository) {
        this.cosechaRepository = cosechaRepository;
        this.cultivoRepository = cultivoRepository;
    }

    // Listar todas las cosechas de un cultivo
    public List<Cosecha> listarCosechasCultivo(Long cultivoId) {
        return cosechaRepository.findByCultivoId(cultivoId);
    }

//    // Crear una nueva cosecha asociada a un cultivo
//    public Cosecha crearCosecha(Long cultivoId, Cosecha cosecha) {
//        Cultivo cultivo = cultivoRepository.findById(cultivoId)
//                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));
//        cosecha.setCultivo(cultivo);
//        return cosechaRepository.save(cosecha);
//    }
    public Cosecha crearCosecha(Long cultivoId, Cosecha cosecha) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new RuntimeException("⚠️ Error: Cultivo no encontrado con ID " + cultivoId));

        // ✅ Asignar el cultivo correctamente a la cosecha antes de guardarla
        cosecha.setCultivo(cultivo);

        // ✅ Mensaje de depuración
        System.out.println("🌱 Guardando cosecha: " + cosecha.toString());

        return cosechaRepository.save(cosecha);
    }

    // Eliminar una cosecha
    public void eliminarCosecha(Long id) {
        if (!cosechaRepository.existsById(id)) {
            throw new RuntimeException("La cosecha con ID " + id + " no existe.");
        }
        cosechaRepository.deleteById(id);
    }
    // Obtener una cosecha por ID
    public Cosecha obtenerCosecha(Long cosechaId) {
        return cosechaRepository.findById(cosechaId)
                .orElseThrow(() -> new RuntimeException("Cosecha no encontrada"));
    }


}
