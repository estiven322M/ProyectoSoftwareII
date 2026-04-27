package com.universidad.ecologistica.infraestructure.controller;

import org.springframework.web.bind.annotation.*;
import com.universidad.ecologistica.application.usecase.*;
import com.universidad.ecologistica.domain.model.HojaControl;
import java.util.Map;

@RestController
@RequestMapping("/api/hojas-control")
public class HojaControlController {

    private final CrearHojaControlUseCase crearUseCase;
    private final ProcesarHojaControlUseCase procesarUseCase;

    public HojaControlController(CrearHojaControlUseCase crearUseCase, ProcesarHojaControlUseCase procesarUseCase) {
        this.crearUseCase = crearUseCase;
        this.procesarUseCase = procesarUseCase;
    }

    // REQ-019: Crear Hoja Control
    @PostMapping
    public HojaControl crear(@RequestBody HojaControlRequest request) {
        return crearUseCase.ejecutar(request.paqueteEcoetiqueta, request.peso, request.dimensiones);
    }

    // REQ-020: Aceptar Hoja Control
    @PatchMapping("/{id}/aceptar")
    public String aceptar(@PathVariable String id) {
        procesarUseCase.aceptar(id);
        return "Hoja de Control " + id + " aceptada formalmente por el cliente.";
    }

    // REQ-021: Registrar Cargas Rechazadas
    @PatchMapping("/{id}/rechazar")
    public String rechazar(@PathVariable String id, @RequestBody Map<String, String> body) {
        String motivo = body.get("motivo");
        procesarUseCase.rechazar(id, motivo);
        return "Carga rechazada en la Hoja de Control " + id + " por el siguiente motivo: " + motivo;
    }

    // Clase auxiliar para mapear el JSON de entrada
    public static class HojaControlRequest {
        public String paqueteEcoetiqueta;
        public double peso;
        public String dimensiones;
    }
}
