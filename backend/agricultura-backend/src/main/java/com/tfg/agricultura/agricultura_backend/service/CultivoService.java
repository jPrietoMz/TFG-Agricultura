package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.User; // Asegúrate de importar la clase User
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CultivoService {

    private final CultivoRepository cultivoRepository;

    public CultivoService(CultivoRepository cultivoRepository) {
        this.cultivoRepository = cultivoRepository;
    }

    public List<Cultivo> getCultivosPorUsuario(Long usuarioId) {
        return cultivoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Cultivo> getCultivoPorId(Long id) {
        return cultivoRepository.findById(id);
    }

    public Cultivo asignarCultivoUsuario(Long cultivoId, Long usuarioId) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado."));
        User usuario = new User(); // Crear un objeto User
        usuario.setId(usuarioId); // Establecer el ID del usuario
        cultivo.setUsuario(usuario); // Asignar el usuario al cultivo
        return cultivoRepository.save(cultivo);
    }
}
