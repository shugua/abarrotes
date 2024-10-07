package com.fresh.service;

import com.fresh.dto.EnvasadoD;
import com.fresh.repository.EnvasadoRepository;
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
public class EnvasadoService implements Serializable {

    @Autowired
    EnvasadoRepository envasadoRepository;

    public EnvasadoD getByIdEnvasado(Integer idEnvasado) {
        List<Object[]> lstObject = envasadoRepository.getByIdEnvasado(idEnvasado);

        EnvasadoD e = new EnvasadoD();
        lstObject.forEach(o -> {

            e.setIdEnvasadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            e.setIdOrdenFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            e.setIdEntradaMateriaPrimaFK(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            e.setNumeroTarima(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            e.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            e.setKilos(o[5] == null ? null : new BigDecimal(o[5].toString()));
            e.setCantidadEntregada(o[6] == null ? BigDecimal.ZERO : new BigDecimal(o[6].toString()));
            e.setKilosEntregados(o[7] == null ? BigDecimal.ZERO : new BigDecimal(o[7].toString()));
            e.setTamano(o[8] == null ? null : o[8].toString());
            e.setEstatus(o[9] == null ? null : o[9].toString());
            e.setIdTipoProductoFk(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            e.setFecha(o[11] == null ? null : (Date) o[11]);
            e.setFechaCancelacion(o[12] == null ? null : (Date) o[12]);
            e.setIdUsuarioFk(o[13] == null ? null : Integer.valueOf(o[13].toString()));
            e.setIdUsuarioCancelaFk(o[14] == null ? null : Integer.valueOf(o[14].toString()));
            e.setNumeroOrden(o[15] == null ? null : o[15].toString());
            e.setNombreTipoProducto(o[16] == null ? "" : o[16].toString());
            e.setLote(o[17] == null ? null : o[17].toString());

        });

        return e;
    }

    public List<EnvasadoD> getByIdOrdenEntradaAndFechas(Integer idOrdenTrabajo, Integer idEntrada, String fechaInicio, String fechaFin, String estatus) {
        List<EnvasadoD> lstE = new ArrayList<>();
        List<Object[]> lstObject = envasadoRepository.getByIdOrdenEntradaAndFechas(idOrdenTrabajo, idEntrada, fechaInicio, fechaFin, estatus);
        lstObject.forEach(o -> {

            EnvasadoD e = new EnvasadoD();

            e.setIdEnvasadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            e.setIdOrdenFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            e.setIdEntradaMateriaPrimaFK(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            e.setNumeroTarima(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            e.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            e.setKilos(o[5] == null ? null : new BigDecimal(o[5].toString()));
            e.setCantidadEntregada(o[6] == null ? null : new BigDecimal(o[6].toString()));
            e.setKilosEntregados(o[7] == null ? null : new BigDecimal(o[7].toString()));
            e.setTamano(o[8] == null ? null : o[8].toString());
            e.setEstatus(o[9] == null ? null : o[9].toString());
            e.setIdTipoProductoFk(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            e.setFecha(o[11] == null ? null : (Date) o[11]);
            e.setFechaCancelacion(o[12] == null ? null : (Date) o[12]);
            e.setIdUsuarioFk(o[13] == null ? null : Integer.valueOf(o[13].toString()));
            e.setIdUsuarioCancelaFk(o[14] == null ? null : Integer.valueOf(o[14].toString()));
            e.setNumeroOrden(o[15] == null ? null : o[15].toString());
            e.setNombreTipoProducto(o[16] == null ? "" : o[16].toString());
            e.setLote(o[17] == null ? null : o[17].toString());

            lstE.add(e);
        });

        return lstE;
    }

    @Transactional
    public int insert(EnvasadoD e) {
        return envasadoRepository.insert(e.getIdOrdenFk(), e.getNumeroTarima(), e.getIdEntradaMateriaPrimaFK(), e.getCantidad(), e.getKilos(), e.getTamano(), e.getIdTipoProductoFk(), e.getFecha(), e.getIdUsuarioFk(),e.getCantidadCaja(),e.getPesoUnitario(),e.getTaraUnitario(),e.getKilosBruto(),e.getLoteProductoTermiando());
                                                                                                                                                                                                                                                                           
    }

    @Transactional
    public int delete(EnvasadoD e) {
        return envasadoRepository.delete(e.getIdEnvasadoPk());

    }

    @Transactional
    public int cargar(Integer idEnvasado) {
        return envasadoRepository.cargar(idEnvasado);

    }

    public Integer getNumeroTarimaByIdOrden(int idOrdenFk) {
        return envasadoRepository.getNumeroTarimaByIdOrden(idOrdenFk);
    }

    public List<EnvasadoD> getByIdOrdenAndEstatus(Integer idOrdenTrabajo, String estatus) {
        List<EnvasadoD> lstE = new ArrayList<>();
        List<Object[]> lstObject = envasadoRepository.getByIdOrdenAndEstatus(idOrdenTrabajo, estatus);
        lstObject.forEach(o -> {

            EnvasadoD e = new EnvasadoD();

            e.setIdEnvasadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            e.setIdOrdenFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            e.setIdEntradaMateriaPrimaFK(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            e.setNumeroTarima(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            e.setCantidad(o[4] == null ? BigDecimal.ZERO : new BigDecimal(o[4].toString()));
            e.setKilos(o[5] == null ? null : new BigDecimal(o[5].toString()));
            e.setCantidadEntregada(o[6] == null ? BigDecimal.ZERO : new BigDecimal(o[6].toString()));
            e.setKilosEntregados(o[7] == null ? null : new BigDecimal(o[7].toString()));
            e.setTamano(o[8] == null ? null : o[8].toString());
            e.setEstatus(o[9] == null ? null : o[9].toString());
            e.setIdTipoProductoFk(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            e.setFecha(o[11] == null ? null : (Date) o[11]);
            e.setFechaCancelacion(o[12] == null ? null : (Date) o[12]);
            e.setIdUsuarioFk(o[13] == null ? null : Integer.valueOf(o[13].toString()));
            e.setIdUsuarioCancelaFk(o[14] == null ? null : Integer.valueOf(o[14].toString()));
            e.setNumeroOrden(o[15] == null ? null : o[15].toString());
            e.setNombreTipoProducto(o[16] == null ? "" : o[16].toString());
            e.setLote(o[17] == null ? null : o[17].toString());
///seteamos cantidad Salida con cantidad para la pantalla de carga
            e.setCantidadSalida(e.getCantidad().subtract(e.getCantidadEntregada()));
            lstE.add(e);
        });

        return lstE;
    }

    @Transactional
    public int modificaCantidadEntregada(BigDecimal cantidadEntregada, BigDecimal kilosEntregados, String estatus, Integer idEnvasado) {
        return envasadoRepository.modificaCantidadEntregada(cantidadEntregada, kilosEntregados, estatus, idEnvasado);
    }

}
