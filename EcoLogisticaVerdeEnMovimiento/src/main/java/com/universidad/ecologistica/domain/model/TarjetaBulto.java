package com.universidad.ecologistica.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class TarjetaBulto {

    @Id
    private String id;
    
    private String actaConsolidacionId; // Para trazar de qué acta viene
    private String paqueteEcoetiqueta;  // A qué paquete pertenece
    
    @Enumerated(EnumType.STRING)
    private EstadoTarjeta estado;
    
    private LocalDateTime fechaGeneracion;
    private LocalDateTime fechaEntrega;

    protected TarjetaBulto() {}

    private TarjetaBulto(String id, String actaConsolidacionId, String paqueteEcoetiqueta) {
        this.id = id;
        this.actaConsolidacionId = actaConsolidacionId;
        this.paqueteEcoetiqueta = paqueteEcoetiqueta;
        this.estado = EstadoTarjeta.GENERADA;
        this.fechaGeneracion = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getActaConsolidacionId() { return actaConsolidacionId; }
    public String getPaqueteEcoetiqueta() { return paqueteEcoetiqueta; }
    public EstadoTarjeta getEstado() { return estado; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public LocalDateTime getFechaEntrega() { return fechaEntrega; }

    // Comportamiento para REQ-016: Confirmar entrega
    public void confirmarEntrega() {
        if (this.estado == EstadoTarjeta.ENTREGADA) {
            throw new IllegalStateException("La tarjeta de bulto ya fue entregada al cliente.");
        }
        this.estado = EstadoTarjeta.ENTREGADA;
        this.fechaEntrega = LocalDateTime.now();
    }

    // Factory Method (REQ-011)
    public static class Factory {
        public static TarjetaBulto generar(String id, String actaId, String paqueteId) {
            return new TarjetaBulto(id, actaId, paqueteId);
        }
    }
}