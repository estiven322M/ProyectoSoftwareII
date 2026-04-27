package com.universidad.ecologistica.domain.repository;

import com.universidad.ecologistica.domain.model.HojaControl;
import java.util.Optional;

public interface HojaControlRepository {
    void guardar(HojaControl hoja);
    Optional<HojaControl> buscarPorId(String id);
}
