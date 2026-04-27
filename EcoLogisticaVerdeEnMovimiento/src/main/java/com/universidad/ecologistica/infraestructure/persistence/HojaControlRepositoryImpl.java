package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.HojaControl;
import com.universidad.ecologistica.domain.repository.HojaControlRepository;
import java.util.Optional;

@Component
public class HojaControlRepositoryImpl implements HojaControlRepository {

    private final JpaHojaControlRepository jpaRepository;

    public HojaControlRepositoryImpl(JpaHojaControlRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(HojaControl hoja) {
        jpaRepository.save(hoja);
    }

    @Override
    public Optional<HojaControl> buscarPorId(String id) {
        return jpaRepository.findById(id);
    }
}