package com.universidad.ecologistica.infraestructure.controller;

import org.springframework.web.bind.annotation.*;
import com.universidad.ecologistica.application.usecase.CrearFichaUseCase;
import com.universidad.ecologistica.application.usecase.AprobarFichaUseCase;
import com.universidad.ecologistica.domain.model.FichaAdmision;
import com.universidad.ecologistica.domain.repository.FichaAdmisionRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fichas")
public class FichaController {

    private final CrearFichaUseCase crearFichaUseCase;
    private final AprobarFichaUseCase aprobarFichaUseCase;
    private final FichaAdmisionRepository repository; 

    public FichaController(CrearFichaUseCase crearFichaUseCase, 
                           AprobarFichaUseCase aprobarFichaUseCase,
                           FichaAdmisionRepository repository) {
        this.crearFichaUseCase = crearFichaUseCase;
        this.aprobarFichaUseCase = aprobarFichaUseCase;
        this.repository = repository;
    }

    // REQ-001: Crear Ficha
    @PostMapping
    public FichaAdmision crearFicha(@RequestBody Map<String, String> request) {
        // Obtenemos el comprobanteId que envía el frontend en el JSON
        return crearFichaUseCase.ejecutar(request.get("comprobanteId"));
    }

    // REQ-002: Consultar Ficha
    @GetMapping("/{id}")
    public FichaAdmision consultarFicha(@PathVariable String id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ficha no encontrada"));
    }
    
    @GetMapping
    public List<FichaAdmision> listarTodas() {
        return repository.listarTodas();
    }

    // REQ-003: Aprobar Ficha
    @PatchMapping("/{id}/aprobar")
    public String aprobarFicha(@PathVariable String id) {
        aprobarFichaUseCase.ejecutar(id);
        return "Ficha aprobada correctamente.";
    }
}
