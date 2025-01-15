//package com.tfg.agricultura.agricultura_backend.config;
//
//import com.tfg.agricultura.agricultura_backend.model.Cultivo;
//import com.tfg.agricultura.agricultura_backend.repository.CultivoRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class CultivoInitConfig {
//
//    @Bean
//    public CommandLineRunner initCultivos(CultivoRepository cultivoRepository) {
//        return args -> {
//            List<String> cultivosIniciales = List.of("Higos", "Aceituna verde", "Aceituna negra", "Uva");
//
//            for (String nombreCultivo : cultivosIniciales) {
//                if (cultivoRepository.findByNombre(nombreCultivo) == null) {
//                    Cultivo cultivo = new Cultivo();
//                    cultivo.setNombre(nombreCultivo);
//                    cultivo.setDescripcion("Descripción de " + nombreCultivo); // Puedes cambiarlo si lo deseas
//                    cultivo.setUsuario(null); // Inicialmente no están asignados
//                    cultivoRepository.save(cultivo);
//                }
//            }
//        };
//    }
//}
