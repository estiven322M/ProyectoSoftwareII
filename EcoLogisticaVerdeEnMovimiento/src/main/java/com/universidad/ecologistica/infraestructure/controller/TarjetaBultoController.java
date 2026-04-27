package com.universidad.ecologistica.infraestructure.controller;

import org.springframework.web.bind.annotation.*;
import com.universidad.ecologistica.application.usecase.*;
import com.universidad.ecologistica.domain.model.TarjetaBulto;
import java.util.Map;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaBultoController {

    private final GenerarTarjetaUseCase generarUseCase;
    private final ConfirmarEntregaTarjetaUseCase confirmarEntregaUseCase;

    public TarjetaBultoController(GenerarTarjetaUseCase generarUseCase, 
                                  ConfirmarEntregaTarjetaUseCase confirmarEntregaUseCase) {
        this.generarUseCase = generarUseCase;
        this.confirmarEntregaUseCase = confirmarEntregaUseCase;
    }

    // REQ-011 y REQ-015: Generar Tarjeta
    @PostMapping("/generar")
    public TarjetaBulto generar(@RequestBody Map<String, String> body) {
        return generarUseCase.ejecutar(body.get("actaId"), body.get("paqueteEcoetiqueta"));
    }

    // REQ-016: Confirmar Entrega
    @PatchMapping("/{id}/entregar")
    public String confirmarEntrega(@PathVariable String id) {
        confirmarEntregaUseCase.ejecutar(id);
        return "La tarjeta de bulto " + id + " ha sido entregada al cliente y confirmada en el sistema.";
    }
}