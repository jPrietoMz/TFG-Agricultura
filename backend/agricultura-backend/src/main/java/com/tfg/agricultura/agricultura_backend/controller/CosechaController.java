package com.tfg.agricultura.agricultura_backend.controller;

import com.tfg.agricultura.agricultura_backend.model.Cosecha;
import com.tfg.agricultura.agricultura_backend.model.User;
import com.tfg.agricultura.agricultura_backend.repository.CosechaRepository;
import com.tfg.agricultura.agricultura_backend.repository.UserRepository;
import com.tfg.agricultura.agricultura_backend.security.JwtTokenProvider;
import com.tfg.agricultura.agricultura_backend.service.CosechaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cosechas")
public class CosechaController {

    private final CosechaService cosechaService;
    private final CosechaRepository cosechaRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public CosechaController(CosechaService cosechaService, CosechaRepository cosechaRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.cosechaService = cosechaService;
        this.cosechaRepository = cosechaRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Listar cosechas de un cultivo
    @GetMapping("/cultivo/{cultivoId}")
    public ResponseEntity<List<Cosecha>> listarCosechas(@PathVariable Long cultivoId) {
        List<Cosecha> cosechas = cosechaService.listarCosechasCultivo(cultivoId);
        return ResponseEntity.ok(cosechas);
    }

    @PostMapping("/cultivo/{cultivoId}")
    public ResponseEntity<Cosecha> crearCosecha(
            @PathVariable Long cultivoId,
            @RequestBody Cosecha cosecha,
            @RequestHeader("Authorization") String token) {

        String username = jwtTokenProvider.getUsername(token.replace("Bearer ", ""));
        Cosecha nuevaCosecha = cosechaService.crearCosecha(cultivoId, cosecha, username);
        return ResponseEntity.ok(nuevaCosecha);
    }

    @GetMapping
    public ResponseEntity<List<Cosecha>> getCosechas(@RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsername(token.replace("Bearer ", ""));
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Cosecha> cosechas = cosechaRepository.findByUsuarioId(usuario.getId());
        return ResponseEntity.ok(cosechas);
    }


    @PostMapping
    public ResponseEntity<Cosecha> createCosecha(@RequestBody Cosecha cosecha, @RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsername(token);
        User usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        cosecha.setUsuario(usuario);
        Cosecha nuevaCosecha = cosechaRepository.save(cosecha);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCosecha);
    }

    @GetMapping("/mis-cosechas")
    public ResponseEntity<List<Cosecha>> listarMisCosechas(@RequestHeader("Authorization") String token) {
        List<Cosecha> cosechas = cosechaService.listarCosechasPorUsuario(token);
        return ResponseEntity.ok(cosechas);
    }

    // Obtener una cosecha específica
    @GetMapping("/{cosechaId}")
    public ResponseEntity<Cosecha> obtenerCosecha(@PathVariable Long cosechaId) {
        Cosecha cosecha = cosechaService.obtenerCosecha(cosechaId);
        return ResponseEntity.ok(cosecha);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCosecha(@PathVariable Long id) {
        cosechaService.eliminarCosecha(id);
        return ResponseEntity.noContent().build();
    }


}
