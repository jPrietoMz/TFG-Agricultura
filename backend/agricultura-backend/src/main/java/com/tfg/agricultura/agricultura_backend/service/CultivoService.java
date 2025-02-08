package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CultivoService {

    private final CultivoRepository cultivoRepository;
    private final UserRepository userRepository;

    public CultivoService(CultivoRepository cultivoRepository, UserRepository userRepository) {
        this.cultivoRepository = cultivoRepository;
        this.userRepository = userRepository;
    }

//    public List<Cultivo> getCultivosPorUsuario(Long usuarioId) {
//        return cultivoRepository.findByUsuario_Id(usuarioId);
//    }
    // Método para obtener cultivos por usuario ID
    public List<Cultivo> obtenerCultivosPorUsuario(Long usuarioId) {
        return cultivoRepository.findByUsuarios_Id(usuarioId);
    }

    public Optional<Cultivo> getCultivoPorId(Long id) {
        return cultivoRepository.findById(id);
    }

    public Cultivo asignarCultivoUsuario(Long cultivoId, Long usuarioId) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado."));
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Agregar el usuario a la lista de usuarios del cultivo
        cultivo.getUsuarios().add(usuario);

        // Agregar el cultivo a la lista de cultivos del usuario (opcional, si es necesario)
        usuario.getCultivos().add(cultivo);

        // Guardar el cultivo con la relación actualizada
        cultivoRepository.save(cultivo);

        return cultivo;
    }

    public Cultivo obtenerCultivoPorId(Long id) {
        return cultivoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cultivo no encontrado"));
    }
}
