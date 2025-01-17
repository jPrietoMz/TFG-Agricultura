package com.tfg.agricultura.agricultura_backend.config;

import com.tfg.agricultura.agricultura_backend.model.Cultivo;
import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CultivoInitConfig {

    @Bean
    public CommandLineRunner initCultivos(CultivoRepository cultivoRepository) {
        return args -> {
            List<String> cultivosIniciales = List.of("Higos", "Aceituna verde", "Aceituna negra", "Uva");

            for (String nombreCultivo : cultivosIniciales) {
                if (!cultivoRepository.existsByNombre(nombreCultivo)) {
                    Cultivo cultivo = new Cultivo(nombreCultivo, "Descripción de " + nombreCultivo);
                    cultivoRepository.save(cultivo);
                }

            }
        };
    }
}
