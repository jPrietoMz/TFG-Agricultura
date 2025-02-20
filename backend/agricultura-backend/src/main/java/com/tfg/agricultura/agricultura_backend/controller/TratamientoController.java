package com.tfg.agricultura.agricultura_backend.controller;

import com.tfg.agricultura.agricultura_backend.model.Tratamiento;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.TratamientoRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider;
import com.tfg.agricultura.agricultura_backend.service.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    private final TratamientoService tratamientoService;
    private final TratamientoRepository tratamientoRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TratamientoController(TratamientoService tratamientoService, TratamientoRepository tratamientoRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.tratamientoService = tratamientoService;
        this.tratamientoRepository = tratamientoRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/cultivo/{cultivoId}")
    public ResponseEntity<List<Tratamiento>> listarTratamientos(@PathVariable Long cultivoId) {
        return ResponseEntity.ok(tratamientoService.listarTratamientosPorCultivo(cultivoId));
    }

    @PostMapping("/cultivo/{cultivoId}")
    public ResponseEntity<Tratamiento> crearTratamiento(
            @PathVariable Long cultivoId,
            @RequestBody Tratamiento tratamiento,
            @RequestHeader("Authorization") String token) {

        String username = jwtTokenProvider.getUsername(token.replace("Bearer ", ""));
        Tratamiento nuevoTratamiento = tratamientoService.crearTratamiento(cultivoId, tratamiento, username);
        return ResponseEntity.ok(nuevoTratamiento);
    }

    @GetMapping("/{tratamientoId}")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable Long tratamientoId) {
        return ResponseEntity.ok(tratamientoService.obtenerTratamiento(tratamientoId));
    }

    @DeleteMapping("/{tratamientoId}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long tratamientoId) {
        tratamientoService.eliminarTratamiento(tratamientoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Tratamiento>> getTratamientos(@RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsername(token.replace("Bearer ", ""));
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Tratamiento> cosechas = tratamientoRepository.findByUsuarioId(usuario.getId());
        return ResponseEntity.ok(cosechas);
    }

    @PostMapping
    public ResponseEntity<Tratamiento> createTratamiento(@RequestBody Tratamiento tratamiento, @RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsername(token);
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        tratamiento.setUsuario(usuario);
        Tratamiento nuevoTratamiento = tratamientoRepository.save(tratamiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTratamiento);
    }

    @GetMapping("/mis-tratamientos")
    public ResponseEntity<List<Tratamiento>> listarMisTratamientos(@RequestHeader("Authorization") String token) {
        List<Tratamiento> tratamientos = tratamientoService.listarTratamientosPorUsuario(token);
        return ResponseEntity.ok(tratamientos);
    }


}
