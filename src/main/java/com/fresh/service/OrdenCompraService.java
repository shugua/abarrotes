package com.fresh.service;

import com.fresh.dominio.Producto;
import com.fresh.dto.ProductoD;
import com.fresh.repository.OrdenCompraRepository;
import com.fresh.repository.ProductoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class OrdenCompraService implements Serializable {

    @Autowired
    OrdenCompraRepository productoRepository;

 

    @Transactional
    public int update(ProductoD p) {
        return productoRepository.update(p.getNombre(), p.getCodigo(), p.getTipo(), p.getEstatus(), p.getMinimo(), p.getTiempoEntrega(), p.getIdProductoPk());
    }

    @Transactional
    public int insert(ProductoD p) {
        return productoRepository.insert(p.getNombre(), p.getCodigo(), p.getTipo(), p.getMinimo(), p.getTiempoEntrega(), p.getIdUsuarioAlta());
    }


    private ProductoD convertirEntidadADTO(Producto p) {
        ProductoD dto = new ProductoD();
        dto.setIdProductoPk(p.getIdProductoPk());
        dto.setNombre(p.getNombre());
        dto.setMinimo(p.getMinimo());
        dto.setTiempoEntrega(p.getTiempoEntrega());
        dto.setTipo(p.getTipo());
        dto.setEstatus(p.getEstatus());
        dto.setCodigo(p.getCodigo());
        dto.setClaveUnidad(p.getClaveUnidad());
        dto.setClaveProductoServicio(p.getClaveProductoServicio());

        return dto;
    }

}
