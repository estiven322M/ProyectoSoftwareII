package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.TarjetaBulto;
import com.universidad.ecologistica.domain.repository.TarjetaBultoRepository;
import java.util.Optional;

@Component
public class TarjetaBultoRepositoryImpl implements TarjetaBultoRepository {

    private final JpaTarjetaBultoRepository jpaRepository;

    public TarjetaBultoRepositoryImpl(JpaTarjetaBultoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(TarjetaBulto tarjeta) {
        jpaRepository.save(tarjeta);
    }

    @Override
    public Optional<TarjetaBulto> buscarPorId(String id) {
        return jpaRepository.findById(id);
    }
}
