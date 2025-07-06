package com.ECOMARKET_SPA.MS_SOPORTE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.ProductoDevolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.Ticket;
import com.ECOMARKET_SPA.MS_SOPORTE.repo.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    public Ticket obtenerTicketPorId(int idTicket) {
        return ticketRepository.findByIdTicket(idTicket);
    }

    public List<Ticket> obtenerTicketsPorRut(String rutCliente) {
        return ticketRepository.findByRutCliente(rutCliente);
    }


    public Ticket obtenerIdDeDevolucion(int idDevolucion){
        return ticketRepository.findByDevolucion_IdDevolucion(idDevolucion);
    } 

    public Ticket crearTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    

    public void eliminarTicket(int idTicket) {
        ticketRepository.deleteById(idTicket);
    }


}
