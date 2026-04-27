package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.universidad.ecologistica.domain.model.OrdenDespacho;

@Repository
public interface JpaOrdenDespachoRepository extends JpaRepository<OrdenDespacho, String> {
}
