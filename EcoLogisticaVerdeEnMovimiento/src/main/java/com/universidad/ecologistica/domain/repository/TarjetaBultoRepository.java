package com.universidad.ecologistica.domain.repository;

import com.universidad.ecologistica.domain.model.TarjetaBulto;
import java.util.Optional;

public interface TarjetaBultoRepository {
    void guardar(TarjetaBulto tarjeta);
    Optional<TarjetaBulto> buscarPorId(String id);
}