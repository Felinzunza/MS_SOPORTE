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

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.service.DevolucionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/devoluciones")
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

}
