package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.OrdenDespacho;
import com.universidad.ecologistica.domain.repository.OrdenDespachoRepository;
import java.util.UUID;

@Service
public class CrearOrdenUseCase {

    private final OrdenDespachoRepository repository;

    public CrearOrdenUseCase(OrdenDespachoRepository repository) {
        this.repository = repository;
    }

    public OrdenDespacho ejecutar(String actaId, String ruta) {
        // Generamos un ID amigable para la Orden
        String idGenerado = "ORD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        
        OrdenDespacho nuevaOrden = OrdenDespacho.Factory.crearNuevaOrden(idGenerado, actaId, ruta);
        repository.guardar(nuevaOrden);
        
        return nuevaOrden;
    }
}
