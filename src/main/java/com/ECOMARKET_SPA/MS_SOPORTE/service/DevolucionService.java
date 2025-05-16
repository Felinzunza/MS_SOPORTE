package com.ECOMARKET_SPA.MS_SOPORTE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.repo.DevolucionRepository;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository devolucionRepository;
    
    public List<Devolucion> listarDevoluciones() {
        return devolucionRepository.findAll();
    }

    public Devolucion guardarDevolucion(Devolucion devolucion) {
        return devolucionRepository.save(devolucion);
    }

    public Devolucion obtenerDevolucionPorId(int idDevolucion) {
        return devolucionRepository.findByIdDevolucion(idDevolucion);
    }

    public void eliminarDevolucion(int idDevolucion) {
        devolucionRepository.deleteById(idDevolucion);
        
    }
}
