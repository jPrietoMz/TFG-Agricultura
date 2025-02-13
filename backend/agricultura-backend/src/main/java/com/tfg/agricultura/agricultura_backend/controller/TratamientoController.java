package com.tfg.agricultura.agricultura_backend.controller;

import com.tfg.agricultura.agricultura_backend.model.Tratamiento;
import com.tfg.agricultura.agricultura_backend.service.TratamientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    private final TratamientoService tratamientoService;

    public TratamientoController(TratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }

    // 🔹 Listar tratamientos de un cultivo
    @GetMapping("/cultivo/{cultivoId}")
    public ResponseEntity<List<Tratamiento>> listarTratamientos(@PathVariable Long cultivoId) {
        return ResponseEntity.ok(tratamientoService.listarTratamientosPorCultivo(cultivoId));
    }

    // 🔹 Crear un tratamiento para un cultivo
    @PostMapping("/cultivo/{cultivoId}")
    public ResponseEntity<Tratamiento> crearTratamiento(@PathVariable Long cultivoId, @RequestBody Tratamiento tratamiento) {
        return ResponseEntity.ok(tratamientoService.crearTratamiento(cultivoId, tratamiento));
    }

    // 🔹 Obtener un tratamiento específico
    @GetMapping("/{tratamientoId}")
    public ResponseEntity<Tratamiento> obtenerTratamiento(@PathVariable Long tratamientoId) {
        return ResponseEntity.ok(tratamientoService.obtenerTratamiento(tratamientoId));
    }

    // 🔹 Eliminar un tratamiento
    @DeleteMapping("/{tratamientoId}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long tratamientoId) {
        tratamientoService.eliminarTratamiento(tratamientoId);
        return ResponseEntity.noContent().build();
    }
}
