package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.universidad.ecologistica.domain.model.HojaControl;

@Repository
public interface JpaHojaControlRepository extends JpaRepository<HojaControl, String> {
}
