package com.ECOMARKET_SPA.MS_SOPORTE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Ticket;
import com.ECOMARKET_SPA.MS_SOPORTE.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

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

    @PostMapping
    public ResponseEntity<Ticket>postTicket(@RequestBody Ticket ticket) {
        Ticket buscado = ticketService.obtenerTicketPorId(ticket.getIdTicket());
        if (buscado == null) {
            return new ResponseEntity<>(ticketService.crearTicket(ticket), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
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
}

