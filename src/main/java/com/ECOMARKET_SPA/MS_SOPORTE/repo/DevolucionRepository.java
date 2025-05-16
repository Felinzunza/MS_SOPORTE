package com.ECOMARKET_SPA.MS_SOPORTE.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {

    Devolucion findByIdDevolucion(int idDevolucion);

}
