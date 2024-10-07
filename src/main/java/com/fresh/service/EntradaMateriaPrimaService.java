package com.fresh.service;

import com.fresh.dominio.EntradaMateriaPrima;
import com.fresh.dto.ClienteD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.ProveedorD;
import com.fresh.repository.EntradaMateriaPrimaRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class EntradaMateriaPrimaService implements Serializable {

    @Autowired
    private EntradaMateriaPrimaRepository entradaMateriaPrimaRepository;
    @Autowired
    private EntradaMateriaPrimaProductoService entradaMateriaPrimaProductoService;

    public MateriaPrimaD findByIdEntradaMateriaPrimaPk(Integer idEntradaMateriaPrimaPk) {
        List<Object[]> lstO = entradaMateriaPrimaRepository.findByIdEntradaPk(idEntradaMateriaPrimaPk);
        MateriaPrimaD m = new MateriaPrimaD();
        lstO.forEach(o -> {

            m.setIdEntradaMateriaPrimaPK(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            m.setLote(o[1] == null ? null : o[1].toString());
            m.setFecha(o[2] == null ? null : (Date) o[2]);
            m.setIdProveedorFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            m.setNombreProveedor(o[4] == null ? null : o[4].toString());
            m.setIdClienteFK(o[5] == null ? null : Integer.valueOf(o[5].toString()));
            m.setNombreCliente(o[6] == null ? null : o[6].toString());
            m.setPesoBruto(o[7] == null ? null : new BigDecimal(o[7].toString()));
            m.setPesoNeto(o[8] == null ? null : new BigDecimal(o[8].toString()));
            m.setObservaciones(o[9] == null ? null : o[9].toString());
            m.setIdUsuarioEntradaFK(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            m.setNombreUsuarioEntrada(o[11] == null ? null : o[11].toString());
            m.setEstatus(o[12] == null ? null : o[12].toString());
            m.setCantidadProveedor(o[13] == null ? null : new BigDecimal(o[13].toString()));
            m.setRemision(o[14] == null ? null : o[14].toString());
            ////seteamos el proveedor
            ProveedorD p = new ProveedorD();
            p.setIdProveedorPk(m.getIdProveedorFK());
            p.setNombre(m.getNombreProveedor());
            m.setProveedor(p);
            ////seteamos el proveedor
            ///setea el cliente
            ClienteD c = new ClienteD();
            c.setIdClientePk(m.getIdClienteFK());
            c.setNombre(m.getNombreCliente());
            m.setCliente(c);
            ///setea el cliente
            m.setLstMateriaPrimaProducto(entradaMateriaPrimaProductoService.getByIdEntradaPk(m.getIdEntradaMateriaPrimaPK()));

        });

        return m;

    }

    public List<MateriaPrimaD> findByIdProveedorAndIdClienteAndFecha(Integer idProveedor, Integer idCliente, String fechaInicio, String fechaFin) {
        List<MateriaPrimaD> lstMateriaPrima = new ArrayList<MateriaPrimaD>();
        List<Object[]> lstO = entradaMateriaPrimaRepository.findByIdProveedorAndIdClienteAndFecha(idProveedor, idCliente, fechaInicio, fechaFin);
        lstO.forEach(o -> {
            MateriaPrimaD m = new MateriaPrimaD();
            m.setIdEntradaMateriaPrimaPK(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            m.setLote(o[1] == null ? null : o[1].toString());
            m.setFecha(o[2] == null ? null : (Date) o[2]);
            System.out.println("fecha "+m.getFecha());
            m.setIdProveedorFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            m.setNombreProveedor(o[4] == null ? null : o[4].toString());
            m.setIdClienteFK(o[5] == null ? null : Integer.valueOf(o[5].toString()));
            m.setNombreCliente(o[6] == null ? null : o[6].toString());
            m.setPesoBruto(o[7] == null ? null : new BigDecimal(o[7].toString()));
            m.setPesoNeto(o[8] == null ? null : new BigDecimal(o[8].toString()));
            m.setObservaciones(o[9] == null ? null : o[9].toString());
            m.setIdUsuarioEntradaFK(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            m.setNombreUsuarioEntrada(o[11] == null ? null : o[11].toString());
            m.setEstatus(o[12] == null ? null : o[12].toString());
            m.setCantidadProveedor(o[13] == null ? null : new BigDecimal(o[13].toString()));
            m.setRemision(o[14] == null ? null : o[14].toString());
            ////seteamos el proveedor
            ProveedorD p = new ProveedorD();
            p.setIdProveedorPk(m.getIdProveedorFK());
            p.setNombre(m.getNombreProveedor());
            m.setProveedor(p);
            ////seteamos el proveedor
            ///setea el cliente
            ClienteD c = new ClienteD();
            c.setIdClientePk(m.getIdClienteFK());
            c.setNombre(m.getNombreCliente());
            m.setCliente(c);
            ///setea el cliente
            m.setLstMateriaPrimaProducto(entradaMateriaPrimaProductoService.getByIdEntradaPk(m.getIdEntradaMateriaPrimaPK()));

            lstMateriaPrima.add(m);

        });


        return lstMateriaPrima;

    }

    public int getNextNumeroEntrada() {
        Integer numeroEntrada = entradaMateriaPrimaRepository.getNumeroEntrada();
        return numeroEntrada == null ? 1 : numeroEntrada + 1;
    }

    @Transactional
    public int delete(Integer idEntradaMateriaPrimaPk, Integer idUsuario) {
        return entradaMateriaPrimaRepository.delete(idEntradaMateriaPrimaPk, idUsuario);
    }

    @Transactional
    public int update(MateriaPrimaD m) {
        return entradaMateriaPrimaRepository.update(m.getPesoBruto(), m.getPesoNeto(), m.getTotalTaraTarima(), m.getTotalTaraCaja(), m.getRemision(), m.getCantidadProveedor(), m.getObservaciones(), m.getIdProveedorFK(), m.getIdClienteFK(), m.getIdEntradaMateriaPrimaPK());
    }

    @Transactional
    public int insert(MateriaPrimaD m) {
        m.setEstatus("1");

        EntradaMateriaPrima e = convertirDTOAEntidad(m);
        return entradaMateriaPrimaRepository.save(e).getIdEntradaMateriaPrimaPk();
//        return entradaMateriaPrimaRepository.insert(m.getNumeroEntrada(), m.getPesoBruto(), m.getPesoNeto(), m.getTotalTaraTarima(), m.getTotalTaraCaja(), m.getLote(), m.getRemision(), m.getCantidadProveedor(), m.getObservaciones(), m.getIdUsuarioEntradaFK(), m.getIdProveedorFK(), m.getIdClienteFK());
    }

    private MateriaPrimaD convertirEntidadADTO(EntradaMateriaPrima e) {
        MateriaPrimaD dto = new MateriaPrimaD();
        dto.setIdEntradaMateriaPrimaPK(e.getIdEntradaMateriaPrimaPk());
        dto.setFecha(e.getFecha());
        dto.setFechaCancelacion(e.getFechaCancelacion());
        dto.setEstatus(e.getEstatus());
        dto.setNumeroEntrada(e.getNumeroEntrada());
        dto.setPesoBruto(e.getPesoBruto());
        dto.setPesoNeto(e.getPesoNeto());
        dto.setTotalTaraTarima(e.getTotalTara());
        dto.setTotalTaraCaja(e.getTotalCaja());
        dto.setLote(e.getLote());
        dto.setRemision(e.getRemision());
        dto.setCantidadProveedor(e.getCantidad());
        dto.setObservaciones(e.getObservaciones());
        dto.setIdUsuarioEntradaFK(e.getIdUsuarioFk());
        dto.setIdUsuarioCancelaFK(e.getIdUsuarioCancelaFk());
        dto.setIdProveedorFK(e.getIdProveedorFk());
        dto.setIdClienteFK(e.getIdClienteFk());

        return dto;
    }

    private EntradaMateriaPrima convertirDTOAEntidad(MateriaPrimaD d) {
        EntradaMateriaPrima e = new EntradaMateriaPrima();
        e.setFecha(d.getFecha());
        e.setFechaCancelacion(d.getFechaCancelacion());
        e.setEstatus(d.getEstatus());
        e.setNumeroEntrada(d.getNumeroEntrada());
        e.setPesoBruto(d.getPesoBruto());
        e.setPesoNeto(d.getPesoNeto());
        e.setTotalTara(d.getTotalTaraTarima());
        e.setTotalCaja(d.getTotalTaraCaja());
        e.setLote(d.getLote());
        e.setRemision(d.getRemision());
        e.setCantidad(d.getCantidadProveedor());
        e.setObservaciones(d.getObservaciones());
        e.setIdUsuarioFk(d.getIdUsuarioEntradaFK());
        e.setIdUsuarioCancelaFk(d.getIdUsuarioCancelaFK());
        e.setIdProveedorFk(d.getIdProveedorFK());
        e.setIdClienteFk(d.getIdClienteFK());
        
      

        return e;
    }

    public List<MateriaPrimaD> findByLote(String lote) {
        List<MateriaPrimaD> lstMateriaPrima = new ArrayList<MateriaPrimaD>();
        List<Object[]> lstO = entradaMateriaPrimaRepository.findByLote(lote);
        lstO.forEach(o -> {
            MateriaPrimaD m = new MateriaPrimaD();
            m.setIdEntradaMateriaPrimaPK(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            m.setLote(o[1] == null ? null : o[1].toString());
            m.setFecha(o[2] == null ? null : (Date) o[2]);
            m.setIdProveedorFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            m.setNombreProveedor(o[4] == null ? null : o[4].toString());
            m.setIdClienteFK(o[5] == null ? null : Integer.valueOf(o[5].toString()));
            m.setNombreCliente(o[6] == null ? null : o[6].toString());
            m.setPesoBruto(o[7] == null ? null : new BigDecimal(o[7].toString()));
            m.setPesoNeto(o[8] == null ? null : new BigDecimal(o[8].toString()));
            m.setObservaciones(o[9] == null ? null : o[9].toString());
            m.setIdUsuarioEntradaFK(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            m.setNombreUsuarioEntrada(o[11] == null ? null : o[11].toString());
            m.setEstatus(o[12] == null ? null : o[12].toString());
            ////seteamos el proveedor
            ProveedorD p = new ProveedorD();
            p.setIdProveedorPk(m.getIdProveedorFK());
            p.setNombre(m.getNombreProveedor());
            m.setProveedor(p);
            ////seteamos el proveedor
            ///setea el cliente
            ClienteD c = new ClienteD();
            c.setIdClientePk(m.getIdClienteFK());
            c.setNombre(m.getNombreCliente());
            m.setCliente(c);
            ///setea el cliente

            lstMateriaPrima.add(m);

        });

        return lstMateriaPrima;

    }

}
