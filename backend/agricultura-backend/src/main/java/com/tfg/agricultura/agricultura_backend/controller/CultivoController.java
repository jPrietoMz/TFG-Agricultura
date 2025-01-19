package com.tfg.agricultura.agricultura_backend.controller;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.service.CultivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultivos")
public class CultivoController {

    private final CultivoService cultivoService;

    public CultivoController(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Cultivo>> getCultivosByUsuario(@PathVariable Long usuarioId) {
        List<Cultivo> cultivos = cultivoService.obtenerCultivosPorUsuario(usuarioId);
        return ResponseEntity.ok(cultivos);
    }

    @PostMapping("/{cultivoId}/asignar/{usuarioId}")
    public ResponseEntity<Cultivo> asignarCultivoAUsuario(@PathVariable Long cultivoId, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(cultivoService.asignarCultivoUsuario(cultivoId, usuarioId));
    }
}
