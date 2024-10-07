package com.fresh.service;

import com.fresh.dominio.Producto;
import com.fresh.dto.ProductoD;
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
public class ProductoService implements Serializable {

    @Autowired
    ProductoRepository productoRepository;

    public ArrayList<ProductoD> findAll() {
        List<Producto> lst = productoRepository.findAll();

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ProductoD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

    }
    
    public ArrayList<ProductoD> findByTipo(String estatus) {
        List<Producto> lst = productoRepository.findByTipo(estatus);

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ProductoD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    public ProductoD findByIdProductoPk(Integer idProductoPk) {
        Producto p = productoRepository.findByIdProductoPk(idProductoPk);
        return convertirEntidadADTO(p);

    }

    @Transactional
    public int delete(Integer idProductoPk, Integer idUsuario) {
        return productoRepository.deleteByProductoPk(idProductoPk, idUsuario);
    }

    @Transactional
    public int activar(Integer idProductoPk) {
        return productoRepository.activarByIdProductoPk(idProductoPk);
    }

    @Transactional
    public int update(ProductoD p) {
        return productoRepository.update(p.getNombre(), p.getCodigo(), p.getTipo(), p.getEstatus(), p.getMinimo(), p.getTiempoEntrega(), p.getIdProductoPk(),p.getDescripcion());
    }

    @Transactional
    public int insert(ProductoD p) {
        return productoRepository.insert(p.getNombre(), p.getCodigo(), p.getTipo(), p.getMinimo(), p.getTiempoEntrega(), p.getIdUsuarioAlta(),p.getDescripcion());
    }

    public ArrayList<ProductoD> findByIdNombre(String nombreProducto,String tipo) {
        List<Producto> lst = productoRepository.findByIdNombre(nombreProducto,tipo);

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ProductoD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

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
        dto.setDescripcion(p.getDescripcion());

        return dto;
    }

}
