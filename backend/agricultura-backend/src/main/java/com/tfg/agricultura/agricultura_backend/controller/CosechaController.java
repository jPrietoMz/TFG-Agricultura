package com.tfg.agricultura.agricultura_backend.controller;

import com.tfg.agricultura.agricultura_backend.model.Cosecha;
import com.tfg.agricultura.agricultura_backend.service.CosechaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cosechas")
public class CosechaController {

    private final CosechaService cosechaService;

    public CosechaController(CosechaService cosechaService) {
        this.cosechaService = cosechaService;
    }

    // Listar cosechas de un cultivo
    @GetMapping("/cultivo/{cultivoId}")
    public ResponseEntity<List<Cosecha>> listarCosechas(@PathVariable Long cultivoId) {
        List<Cosecha> cosechas = cosechaService.listarCosechasCultivo(cultivoId);
        return ResponseEntity.ok(cosechas);
    }

//    // Crear una cosecha asociada a un cultivo
//    @PostMapping("/cultivo/{cultivoId}")
//    public ResponseEntity<Cosecha> crearCosecha(@PathVariable Long cultivoId, @RequestBody Cosecha cosecha) {
//        Cosecha nuevaCosecha = cosechaService.crearCosecha(cultivoId, cosecha);
//        return ResponseEntity.ok(nuevaCosecha);
//    }
    @PostMapping("/cultivo/{cultivoId}")
    public ResponseEntity<Cosecha> crearCosecha(@PathVariable Long cultivoId, @RequestBody Cosecha cosecha) {
        System.out.println("📩 Recibiendo solicitud de nueva cosecha: " + cosecha.toString());
        System.out.println("🌱 Cultivo ID en URL: " + cultivoId);

        // ✅ Verificar si `cultivoId` está llegando correctamente
        if (cultivoId == null || cultivoId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        Cosecha nuevaCosecha = cosechaService.crearCosecha(cultivoId, cosecha);
        return ResponseEntity.ok(nuevaCosecha);
    }


//    // Eliminar una cosecha
//    @DeleteMapping("/{cosechaId}")
//    public ResponseEntity<Void> eliminarCosecha(@PathVariable Long cosechaId) {
//        cosechaService.eliminarCosecha(cosechaId);
//        return ResponseEntity.noContent().build();
//    }

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
