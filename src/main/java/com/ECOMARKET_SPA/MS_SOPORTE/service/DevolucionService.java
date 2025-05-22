package com.ECOMARKET_SPA.MS_SOPORTE.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECOMARKET_SPA.MS_SOPORTE.model.Devolucion;
import com.ECOMARKET_SPA.MS_SOPORTE.model.ProductoDevolucion;
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

    //Una vez que vinculo la devolucion al ticket, no puedo eliminarla, solo se eliminara al eliminar el ticket
    public void eliminarDevolucion(int idDevolucion) { 
        devolucionRepository.deleteById(idDevolucion);
        
    }

    //METODOS PARA PRODUCTO DEVOLUCION

    public List<ProductoDevolucion> listarProductosDevolucion(int idDevolucion) {
        Devolucion devolucion = devolucionRepository.findByIdDevolucion(idDevolucion);
        if (devolucion != null) {
            return devolucion.getProductosDevolucion();
        }
        return null;
    }

    //agregar un producto a la devolucion
    public Devolucion guardarProductoDevolucion(int idDevolucion , ProductoDevolucion nuevoproducto) {
        Devolucion devolucion = devolucionRepository.findByIdDevolucion(idDevolucion);
        if (devolucion == null) {
            return null;
        }
        nuevoproducto.setDevolucion(devolucion); // Establece relación inversa
        devolucion.getProductosDevolucion().add(nuevoproducto);
        return devolucionRepository.save(devolucion); // Cascade.ALL guarda también el producto
    }


    //Buscar un producto de devolucion por id
    public ProductoDevolucion obtenerProductoDevolucionPorId(int idDevolucion, int idProductoDevolucion) {
        Devolucion devolucion = devolucionRepository.findByIdDevolucion(idDevolucion);
        if(devolucion == null) {
            return null;
        }
        for(ProductoDevolucion producto : devolucion.getProductosDevolucion()) {
            if(producto.getIdProductoDevolucion() == idProductoDevolucion) {
                return producto;
            }
        }
        return null;
    }

    //Eliminar un producto de devolucion por id
    public void eliminarProductoDevolucion(int idDevolucion, int idProductoDevolucion) {
        Devolucion devolucion = devolucionRepository.findByIdDevolucion(idDevolucion);
        if(devolucion != null) {
            for(ProductoDevolucion producto : devolucion.getProductosDevolucion()) {
                if(producto.getIdProductoDevolucion() == idProductoDevolucion) {
                    devolucion.getProductosDevolucion().remove(producto);
                    devolucionRepository.save(devolucion);
                    break;
                }
            }
        }

    }

    //Modificar cantidad de un producto de devolucion por id
    public ProductoDevolucion modificarProductoDevolucion(int idDevolucion,int idProductoDevolucion, int nuevaCantidad) {
        Devolucion devolucion = devolucionRepository.findByIdDevolucion(idDevolucion);
        if(devolucion == null) {
            return null;
        }
        for(ProductoDevolucion producto : devolucion.getProductosDevolucion()) {
            if(producto.getIdProductoDevolucion() == idProductoDevolucion) {
                producto.setCantidad(nuevaCantidad);
                devolucionRepository.save(devolucion);
                return producto;
            }
        }
        return null;
    } 

}