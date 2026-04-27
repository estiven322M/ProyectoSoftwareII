package com.universidad.ecologistica.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class ActaConsolidacion {

	@Id
	private String id;

	private String clienteId; // Referencia al cliente o paquete
	
	

	@Enumerated(EnumType.STRING)
	private EstadoActa estado;

	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaFirma;

	// constructor vacio del JPA
	protected ActaConsolidacion() {
	}

	// constructor privado (Factory)
	private ActaConsolidacion(String id, String clienteId) {
		this.id = id;
		this.clienteId = clienteId;
		this.estado = EstadoActa.PENDIENTE; // REQ-006 (Estado inicial)
		this.fechaCreacion = LocalDateTime.now();

	}
	
	public String getId() { return id; }
	public String getClienteId() { return clienteId; }
	
	public EstadoActa getEstado() { 
	    return estado; 
	}
	
	public LocalDateTime getFechaCreacion() { return fechaCreacion; }
	public LocalDateTime getFechaFirma() { return fechaFirma; }
	
	// Comportamiento para REQ-005: Registrar Firma
    public void registrarFirma() {
        if (this.estado == EstadoActa.FIRMADA) {
            throw new IllegalStateException("El acta ya ha sido firmada previamente.");
        }
        this.estado = EstadoActa.FIRMADA;
        this.fechaFirma = LocalDateTime.now();
    }
    
 // Factory Method para REQ-004
    public static class Factory {
        public static ActaConsolidacion crearNuevaActa(String id, String clienteId) {
            if (clienteId == null || clienteId.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del cliente es obligatorio para generar el acta.");
            }
            return new ActaConsolidacion(id, clienteId);
        }
    }
}
