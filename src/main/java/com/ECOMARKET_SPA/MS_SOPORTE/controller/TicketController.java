package com.ECOMARKET_SPA.MS_SOPORTE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.EnumEstadoT;
import com.ECOMARKET_SPA.MS_SOPORTE.model.ProductoDevolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.Ticket;
import com.ECOMARKET_SPA.MS_SOPORTE.service.DevolucionService;
import com.ECOMARKET_SPA.MS_SOPORTE.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    /*@Autowired
    private DevolucionService devolucionService;*/

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.listarTickets();
        if(tickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }


    @GetMapping("/{idTicket}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int idTicket) {
        Ticket ticket = ticketService.obtenerTicketPorId(idTicket);
        if(ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/rut/{rutCliente}")
    public ResponseEntity<List<Ticket>> getTicketsByRut(@PathVariable String rutCliente) {
    List<Ticket> tickets = ticketService.obtenerTicketsPorRut(rutCliente);
    if (tickets.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping
public ResponseEntity<Ticket> postTicket(@RequestBody Ticket ticket) {
    // Si ya existe un ticket con ese ID, devuelve conflicto
    if (ticketService.obtenerTicketPorId(ticket.getIdTicket()) != null) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // Manejo de devolución si viene como parte del ticket
    Devolucion devolucion = ticket.getDevolucion();
    if (devolucion != null && devolucion.getProductosDevolucion() != null) {
        for (ProductoDevolucion p : devolucion.getProductosDevolucion()) {
            p.setDevolucion(devolucion); // Establece la relación inversa
        }
    }

    return new ResponseEntity<>(ticketService.crearTicket(ticket), HttpStatus.CREATED);
}



    @DeleteMapping("/{idTicket}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int idTicket) {
        Ticket ticket = ticketService.obtenerTicketPorId(idTicket);
        if(ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.eliminarTicket(idTicket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{idTicket}/respuesta") //localhost:8083/api/ticket/1/respuesta?respuesta=Hola
    public ResponseEntity<Ticket> updateRespuestaTicket(@PathVariable int idTicket, @RequestParam String respuesta) {
        Ticket ticket = ticketService.obtenerTicketPorId(idTicket);
        if(ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticket.setRespuesta(respuesta);
        return new ResponseEntity<>(ticketService.crearTicket(ticket), HttpStatus.OK);
    }
    @PatchMapping("/{idTicket}/cambiarEstado") //http://localhost:8083/api/tickets/10/cambiarEstado?estado=CERRADO
    public ResponseEntity<Ticket> updateEstadoTicket(@PathVariable int idTicket, @RequestParam EnumEstadoT estado) {
        Ticket ticket = ticketService.obtenerTicketPorId(idTicket);
        if(ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticket.setEstadoticket(estado);
        return new ResponseEntity<>(ticketService.crearTicket(ticket), HttpStatus.OK);
    }



}


    
    

