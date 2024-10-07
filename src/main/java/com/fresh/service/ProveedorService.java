package com.fresh.service;

import com.fresh.dominio.Proveedor;
import com.fresh.dto.ProveedorD;
import com.fresh.repository.ProveedorRepository;
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
public class ProveedorService implements Serializable {

    @Autowired
    ProveedorRepository proveedorRepository;

    public ArrayList<ProveedorD> findAll() {
        List<Proveedor> lst = proveedorRepository.findAll();

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ProveedorD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

    }

    public ArrayList<ProveedorD> findByTipo(String tipo) {
        List<Proveedor> lst = proveedorRepository.findByTipo(tipo);

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ProveedorD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

    }

    public ProveedorD findByIdProveedorPk(Integer idProveedorPk) {
        return convertirEntidadADTO(proveedorRepository.findByIdProveedorPk(idProveedorPk));

    }

    @Transactional
    public int delete(Integer idProveedorPk, Integer idUsuario) {
        return proveedorRepository.deleteByIdProveedorPk(idProveedorPk, idUsuario);
    }

    @Transactional
    public int activar(Integer idProveedorPk) {
        return proveedorRepository.activarByIdProveedorPk(idProveedorPk);
    }

    @Transactional
    public int update(ProveedorD p) {
        return proveedorRepository.update(p.getNombre(), p.getNumeroTelefono(), p.getEstatus(), p.getClave(), p.getCorreo(), p.getTipoProveedor(), p.getDivisa(), p.getFormaPago(), p.getTerminoPago(), p.getRfc(), p.getDomicilio(), p.getIdProveedorPk());
    }

    @Transactional
    public int insert(ProveedorD p) {
        return proveedorRepository.insert(p.getNombre(), p.getNumeroTelefono(), p.getEstatus(), p.getClave(), p.getIdUsuarioAlta(), p.getCorreo(), p.getTipoProveedor(), p.getDivisa(), p.getFormaPago(), p.getTerminoPago(), p.getRfc(), p.getDomicilio());
    }

    public ArrayList<ProveedorD> findByIdNombre(String nombreProveedor,String tipo) {
        List<Proveedor> lst = proveedorRepository.findByIdNombre(nombreProveedor,tipo);

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ProveedorD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

    }

    public Integer getNextValTipoProveedor(Integer tipoProveedor) {
        return proveedorRepository.getNextValTipoProveedor(tipoProveedor);
    }

    private ProveedorD convertirEntidadADTO(Proveedor p) {
        ProveedorD dto = new ProveedorD();
        dto.setIdProveedorPk(p.getIdProveedorPk());
        dto.setNombre(p.getNombre());
        dto.setNumeroTelefono(p.getNumeroTelefono());
        dto.setEstatus(p.getEstatus());
        dto.setClave(p.getEstatus());
        dto.setClave(p.getClave());
        dto.setCorreo(p.getCorreo());
        dto.setDivisa(p.getDivisa());
        dto.setTerminoPago(p.getTerminoPago());
        dto.setFormaPago(p.getFormaPago());
        dto.setTipoProveedor(p.getTipoProveedor());
        dto.setRfc(p.getRfc());
        dto.setDomicilio(p.getDomicilio());

        return dto;
    }

}
