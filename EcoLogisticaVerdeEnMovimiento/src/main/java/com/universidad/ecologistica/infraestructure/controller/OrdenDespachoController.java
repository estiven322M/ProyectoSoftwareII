package com.universidad.ecologistica.infraestructure.controller;

import org.springframework.web.bind.annotation.*;
import com.universidad.ecologistica.application.usecase.*;
import com.universidad.ecologistica.domain.model.OrdenDespacho;
import com.universidad.ecologistica.domain.repository.OrdenDespachoRepository;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenDespachoController {

    private final CrearOrdenUseCase crearUseCase;
    private final AprobarOrdenUseCase aprobarUseCase;
    private final OrdenDespachoRepository repository; // REQ-009 (Consultar)

    public OrdenDespachoController(CrearOrdenUseCase crearUseCase, 
                                   AprobarOrdenUseCase aprobarUseCase,
                                   OrdenDespachoRepository repository) {
        this.crearUseCase = crearUseCase;
        this.aprobarUseCase = aprobarUseCase;
        this.repository = repository;
    }

    // REQ-007: Crear Orden
    @PostMapping
    public OrdenDespacho crear(@RequestBody Map<String, String> body) {
        return crearUseCase.ejecutar(body.get("actaId"), body.get("ruta"));
    }

    // REQ-008: Actualizar Estado (Aprobar por el cliente)
    @PatchMapping("/{id}/aprobar")
    public String aprobar(@PathVariable String id) {
        aprobarUseCase.ejecutar(id);
        return "La orden " + id + " ha sido aprobada por el cliente exitosamente.";
    }

    // REQ-009: Consultar Orden
    @GetMapping("/{id}")
    public OrdenDespacho consultar(@PathVariable String id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Orden de despacho no encontrada."));
    }
}