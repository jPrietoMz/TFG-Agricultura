package com.tfg.agricultura.agricultura_backend.service;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.model.Tratamiento;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import com.tfg.agricultura.agricultura_backend.repository.TratamientoRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final CultivoRepository cultivoRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TratamientoService(TratamientoRepository tratamientoRepository, CultivoRepository cultivoRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.tratamientoRepository = tratamientoRepository;
        this.cultivoRepository = cultivoRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 🔹 Listar todos los tratamientos de un cultivo
    public List<Tratamiento> listarTratamientosPorCultivo(Long cultivoId) {
        return tratamientoRepository.findByCultivoId(cultivoId);
    }

    // 🔹 Crear un nuevo tratamiento asociado a un cultivo
    public Tratamiento crearTratamiento(Long cultivoId, Tratamiento tratamiento, String username) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("⚠️ Error: Usuario no encontrado con username " + username));
        tratamiento.setCultivo(cultivo);
        tratamiento.setUsuario(usuario);
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

    public List<Tratamiento> listarTratamientosPorUsuario(String token) {
        String username = jwtTokenProvider.getUsername(token.replace("Bearer ", ""));
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return tratamientoRepository.findByUsuarioId(usuario.getId());
    }

}
