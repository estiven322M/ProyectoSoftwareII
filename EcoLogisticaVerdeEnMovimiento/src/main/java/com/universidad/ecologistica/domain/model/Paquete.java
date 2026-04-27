package com.universidad.ecologistica.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Paquete {

    @Id
    private String ecoetiqueta; // El ID del paquete es su propia ecoetiqueta
    
    @Enumerated(EnumType.STRING)
    private EstadoPaquete estado;
    
    private String observaciones;

    // Requisito de JPA
    protected Paquete() {}

    // Constructor privado
    private Paquete(String ecoetiqueta) {
        this.ecoetiqueta = ecoetiqueta;
        this.estado = EstadoPaquete.PENDIENTE_VERIFICACION;
    }

    // Getters
    public String getEcoetiqueta() { return ecoetiqueta; }
    public EstadoPaquete getEstado() { return estado; }
    public String getObservaciones() { return observaciones; }

    // Comportamiento para REQ-010: Aceptar el paquete
    public void aceptar() {
        if (this.estado != EstadoPaquete.PENDIENTE_VERIFICACION) {
            throw new IllegalStateException("Solo se pueden aceptar paquetes pendientes.");
        }
        this.estado = EstadoPaquete.ACEPTADO;
        this.observaciones = "Verificación física y en sistema exitosa.";
    }

    // Comportamiento para REQ-014: Rechazar el paquete
    public void rechazar(String motivo) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("Para rechazar un paquete, debe proveer una observación obligatoria.");
        }
        this.estado = EstadoPaquete.RECHAZADO;
        this.observaciones = motivo;
    }

    // Factory Method (Para simular que los paquetes ya existen en el sistema antes de que el Jefe los revise)
    public static class Factory {
        public static Paquete registrarEnSistema(String ecoetiqueta) {
            return new Paquete(ecoetiqueta);
        }
    }
}
