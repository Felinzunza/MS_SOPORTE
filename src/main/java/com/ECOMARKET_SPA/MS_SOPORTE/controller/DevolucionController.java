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
import com.ECOMARKET_SPA.MS_SOPORTE.model.ProductoDevolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.service.DevolucionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/tickets/{ticketId}/devolucion")
public class DevolucionController {

    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public ResponseEntity<Devolucion> getDevolucionPorTicket(@PathVariable int ticketId) {
    Devolucion devolucion = devolucionService.obtenerDevolucionPorTicket(ticketId);
    if (devolucion == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(devolucion, HttpStatus.OK);
}

    // Listar productos devueltos de la devolución del ticket
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDevolucion>> getAllProductosDevolucion(@PathVariable int ticketId) {
        List<ProductoDevolucion> productos = devolucionService.listarProductosDevolucionPorTicket(ticketId);
        if (productos == null || productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Agregar producto a devolución
    @PostMapping("/productos")
    public ResponseEntity<ProductoDevolucion> postProductoDevolucion(@PathVariable int ticketId, @RequestBody ProductoDevolucion nuevoProducto) {
        ProductoDevolucion creado = devolucionService.agregarProductoDevolucion(ticketId, nuevoProducto);
        if (creado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // Obtener producto devuelto por ID
    @GetMapping("/productos/{idProducto}")
    public ResponseEntity<ProductoDevolucion> getProductoDevolucionById(@PathVariable int ticketId, @PathVariable int idProducto) {
        ProductoDevolucion producto = devolucionService.obtenerProductoDevolucionPorId(ticketId, idProducto);
        if (producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    // Eliminar producto devuelto
    @DeleteMapping("/productos/{idProducto}")
    public ResponseEntity<Void> deleteProductoDevolucion(@PathVariable int ticketId, @PathVariable int idProducto) {
        boolean eliminado = devolucionService.eliminarProductoDevolucion(ticketId, idProducto);
        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Modificar cantidad de producto devuelto
    @PatchMapping("/productos/{idProducto}")
    public ResponseEntity<ProductoDevolucion> updateCantProductoDevolucion(@PathVariable int ticketId,
                                                                            @PathVariable int idProducto,
                                                                            @RequestParam int cantidad) {
        if (cantidad < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProductoDevolucion actualizado = devolucionService.modificarCantidadProducto(ticketId, idProducto, cantidad);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
}
