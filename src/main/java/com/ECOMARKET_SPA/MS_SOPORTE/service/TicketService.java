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

    public boolean eliminarSoloLaDevolucion(int idTicket) {
    Ticket ticket = obtenerTicketPorId(idTicket);
    if (ticket == null || ticket.getDevolucion() == null) {
        return false;
    }

    // Romper relaciones bidireccionales
    Devolucion devolucion = ticket.getDevolucion();
    devolucion.setTicket(null);
    ticket.setDevolucion(null);

    // Guardar cambios
    crearTicket(ticket);

    return true;
}

public Devolucion agregarDevolucionAlTicket(int idTicket, Devolucion devolucion) {
    Ticket ticket = obtenerTicketPorId(idTicket);

    if (ticket == null) {
        return null; // No existe el ticket
    }

    if (ticket.getDevolucion() != null) {
        return null; // Ya tiene una devolución asociada → no se permite más de una
    }

    // Establecer relaciones bidireccionales
    devolucion.setTicket(ticket);
    if (devolucion.getProductosDevolucion() != null) {
        for (ProductoDevolucion p : devolucion.getProductosDevolucion()) {
            p.setDevolucion(devolucion);
        }
    }

    ticket.setDevolucion(devolucion);

    // Al tener cascade = ALL en ticket.setDevolucion, esto también guarda la devolución y sus productos
    crearTicket(ticket);

    return devolucion;
}



}
