package com.ECOMARKET_SPA.MS_SOPORTE.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    Ticket findByIdTicket(int idTicket); 

    Ticket findByDevolucion_IdDevolucion(int idDevolucion);;

}
