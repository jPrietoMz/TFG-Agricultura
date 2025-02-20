package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.model.Cosecha;
import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.CosechaRepository;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosechaService {

    private final CosechaRepository cosechaRepository;
    private final CultivoRepository cultivoRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public CosechaService(CosechaRepository cosechaRepository, CultivoRepository cultivoRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.cosechaRepository = cosechaRepository;
        this.cultivoRepository = cultivoRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Listar todas las cosechas de un cultivo
    public List<Cosecha> listarCosechasCultivo(Long cultivoId) {
        return cosechaRepository.findByCultivoId(cultivoId);
    }

    public Cosecha crearCosecha(Long cultivoId, Cosecha cosecha, String username) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new RuntimeException("⚠ Error: Cultivo no encontrado con ID " + cultivoId));
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("⚠ Error: Usuario no encontrado con username " + username));

        cosecha.setCultivo(cultivo);
        cosecha.setUsuario(usuario);

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

    public List<Cosecha> listarCosechasPorUsuario(String token) {
        String username = jwtTokenProvider.getUsername(token.replace("Bearer ", ""));
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return cosechaRepository.findByUsuarioId(usuario.getId());
    }


}
