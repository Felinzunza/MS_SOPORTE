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
import org.springframework.web.bind.annotation.RestController;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.ProductoDevolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.service.DevolucionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {
    
    @Autowired
    private DevolucionService devolucionService;

    @GetMapping
    public ResponseEntity<List<Devolucion>> getAllDevoluciones() {
        List<Devolucion> devoluciones = devolucionService.listarDevoluciones();
        if(devoluciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(devoluciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> getDevolucionById(@PathVariable int id) {
        Devolucion devolucion = devolucionService.obtenerDevolucionPorId(id);
        if(devolucion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(devolucion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Devolucion> postDevolucion(@RequestBody Devolucion devolucion) {
        Devolucion buscado = devolucionService.obtenerDevolucionPorId(devolucion.getIdDevolucion());
        if (buscado == null) {
            return new ResponseEntity<>(devolucionService.guardarDevolucion(devolucion), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevolucion(@PathVariable int id) {
        Devolucion devolucion = devolucionService.obtenerDevolucionPorId(id);
        if(devolucion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        devolucionService.eliminarDevolucion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}/productosdevueltos")
    public ResponseEntity<List<ProductoDevolucion>> getAllProductosDevolucion(@PathVariable int id) {
        List<ProductoDevolucion> productos = devolucionService.listarProductosDevolucion(id);
        if(productos == null || productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    //Agregar un producto a la devolucion
    @PostMapping("{id}/productosdevueltos")
    public ResponseEntity<ProductoDevolucion> postProductoDevolucion(@PathVariable int id, @RequestBody ProductoDevolucion nuevoproducto) {
        Devolucion devolucion = devolucionService.guardarProductoDevolucion(id, nuevoproducto);
        if(devolucion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nuevoproducto, HttpStatus.CREATED);

    }


    //Buscar un producto de devolucion por id
    @GetMapping("{id}/productosdevueltos/{idProductoDevolucion}")
    public ResponseEntity<ProductoDevolucion> getProductoDevolucionById(@PathVariable int id, @PathVariable int idProductoDevolucion) {
        ProductoDevolucion producto = devolucionService.obtenerProductoDevolucionPorId(id, idProductoDevolucion);
        if(producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(producto, HttpStatus.OK);
     }


    //Eliminar un producto de devolucion por id
    @DeleteMapping("{id}/productosdevueltos/{idProductoDevolucion}")
    public ResponseEntity<Void> deleteProductoDevolucion(@PathVariable int id, @PathVariable int idProductoDevolucion) {
        ProductoDevolucion producto = devolucionService.obtenerProductoDevolucionPorId(id, idProductoDevolucion);
        if(producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        devolucionService.eliminarProductoDevolucion(id, idProductoDevolucion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Cambiar cantidad de un producto de devolucion por id
    @PatchMapping("{id}/productosdevueltos/{idProductoDevolucion}")
    public ResponseEntity<ProductoDevolucion> updateCantProductoDevolucion(@PathVariable int id, @PathVariable int idProductoDevolucion, @RequestBody ProductoDevolucion producto) {
        ProductoDevolucion productodev = devolucionService.obtenerProductoDevolucionPorId(id, idProductoDevolucion);
        if(productodev == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(producto.getCantidad() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productodev.setCantidad(producto.getCantidad());
        devolucionService.guardarProductoDevolucion(id, productodev);
        return new ResponseEntity<>(productodev, HttpStatus.OK);
    }
}
