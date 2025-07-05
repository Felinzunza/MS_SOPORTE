package com.ECOMARKET_SPA.MS_SOPORTE.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {

    List<Devolucion> findByTicketIdTicket(int ticketId);

    Devolucion findByIdDevolucion(int idDevolucion);
    

}
