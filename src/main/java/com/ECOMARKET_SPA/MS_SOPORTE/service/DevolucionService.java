package com.ECOMARKET_SPA.MS_SOPORTE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.ProductoDevolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.Ticket;
import com.ECOMARKET_SPA.MS_SOPORTE.repo.DevolucionRepository;
import com.ECOMARKET_SPA.MS_SOPORTE.repo.TicketRepository;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private TicketRepository ticketRepository;


    // obtener devolucion del ticket
    public Devolucion obtenerDevolucionPorTicket(int ticketId) {
    Devolucion devolucion = obtenerDevolucionPorTicketId(ticketId);
    if (devolucion == null) {
        return null;
    }
    return devolucion;
    }

    // Obtener la devolución asociada al ticket
    private Devolucion obtenerDevolucionPorTicketId(int ticketId) {
        Ticket ticket = ticketRepository.findByIdTicket(ticketId);
        if (ticket != null) {
            return ticket.getDevolucion();
        }
        return null;
    }

    // Listar productos devueltos por ticket
    public List<ProductoDevolucion> listarProductosDevolucionPorTicket(int ticketId) {
        Devolucion devolucion = obtenerDevolucionPorTicketId(ticketId);
        if (devolucion != null) {
            return devolucion.getProductosDevolucion();
        }
        return null;
    }

    // Agregar un producto devuelto
    public ProductoDevolucion agregarProductoDevolucion(int ticketId, ProductoDevolucion nuevoProducto) {
        Devolucion devolucion = obtenerDevolucionPorTicketId(ticketId);
        if (devolucion == null) {
            return null;
        }
        System.out.println(">>> codProducto recibido: " + nuevoProducto.getCodProducto());
        nuevoProducto.setDevolucion(devolucion);
        devolucion.getProductosDevolucion().add(nuevoProducto);
        devolucionRepository.save(devolucion);
        return nuevoProducto;
    }

    // Obtener un producto devuelto específico
    public ProductoDevolucion obtenerProductoDevolucionPorId(int ticketId, int idProducto) {
        Devolucion devolucion = obtenerDevolucionPorTicketId(ticketId);
        if (devolucion == null) {
            return null;
        }

        List<ProductoDevolucion> productos = devolucion.getProductosDevolucion();
        for (ProductoDevolucion p : productos) {
            if (p.getIdProductoDevolucion() == idProducto) {
                return p;
            }
        }
        return null;
    }

    // Modificar la cantidad de un producto devuelto
    public ProductoDevolucion modificarCantidadProducto(int ticketId, int idProducto, int nuevaCantidad) {
        Devolucion devolucion = obtenerDevolucionPorTicketId(ticketId);
        if (devolucion == null) {
            return null;
        }

        List<ProductoDevolucion> productos = devolucion.getProductosDevolucion();
        for (ProductoDevolucion p : productos) {
            if (p.getIdProductoDevolucion() == idProducto) {
                p.setCantidad(nuevaCantidad);
                devolucionRepository.save(devolucion);
                return p;
            }
        }
        return null;
    }

    // Eliminar un producto devuelto
    public boolean eliminarProductoDevolucion(int ticketId, int idProducto) {
        Devolucion devolucion = obtenerDevolucionPorTicketId(ticketId);
        if (devolucion == null) {
            return false;
        }

        List<ProductoDevolucion> productos = devolucion.getProductosDevolucion();
        for (ProductoDevolucion p : productos) {
            if (p.getIdProductoDevolucion() == idProducto) {
                productos.remove(p);
                devolucionRepository.save(devolucion);
                return true;
            }
        }
        return false;
    }


}
