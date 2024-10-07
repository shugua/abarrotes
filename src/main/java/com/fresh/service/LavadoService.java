package com.fresh.service;

import com.fresh.dto.Lavado;
import com.fresh.repository.LavadoRepository;
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
public class LavadoService implements Serializable {

    @Autowired
    LavadoRepository lavadoRepository;

    public Lavado getByIdLavado(Integer idLavado) {
        List<Object[]> lstObject = lavadoRepository.getByIdLavado(idLavado);

        Lavado l = new Lavado();
        lstObject.forEach(o -> {

            l.setIdLavadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            l.setFecha(o[1] == null ? null : (Date) o[1]);
            l.setIdOrdenFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            l.setIdEntradaMateriaPrimaFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            l.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            l.setPesoNeto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            l.setObservaciones(o[6] == null ? "" : o[6].toString());
            l.setIdTipoProductoFk(o[7] == null ? null : Integer.valueOf(o[7].toString()));
            l.setIdUsuarioFk(o[8] == null ? null : Integer.valueOf(o[8].toString()));
            l.setLoteMateriaPrima(o[9] == null ? null : o[9].toString());
            l.setNumeroOrdenTrabajo(o[10] == null ? null : o[10].toString());
            l.setDescripcionTipoProducto(o[11] == null ? null : o[11].toString());
            l.setNombreUsuario(o[12] == null ? null : o[12].toString());
            l.setEstatus(o[13] == null ? null : o[13].toString());

        });

        return l;
    }

    public List<Lavado> getByIdOrdenEntradaAndFechas(Integer idOrdenTrabajo, Integer idEntrada, String fechaInicio, String fechaFin, String estatus) {
        List<Lavado> lstL = new ArrayList<>();
        List<Object[]> lstObject = lavadoRepository.getByIdOrdenEntradaAndFechas(idOrdenTrabajo, idEntrada, fechaInicio, fechaFin, estatus);
        lstObject.forEach(o -> {

            Lavado l = new Lavado();

            l.setIdLavadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            l.setFecha(o[1] == null ? null : (Date) o[1]);
            l.setIdOrdenFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            l.setIdEntradaMateriaPrimaFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            l.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            l.setPesoNeto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            l.setObservaciones(o[6] == null ? "" : o[6].toString());
            l.setIdTipoProductoFk(o[7] == null ? null : Integer.valueOf(o[7].toString()));
            l.setIdUsuarioFk(o[8] == null ? null : Integer.valueOf(o[8].toString()));
            l.setLoteMateriaPrima(o[9] == null ? null : o[9].toString());
            l.setNumeroOrdenTrabajo(o[10] == null ? null : o[10].toString());
            l.setDescripcionTipoProducto(o[11] == null ? null : o[11].toString());
            l.setNombreUsuario(o[12] == null ? null : o[12].toString());
            l.setEstatus(o[13] == null ? null : o[13].toString());

            lstL.add(l);
        });

        return lstL;
    }

    @Transactional
    public int insert(Lavado l) {
        return lavadoRepository.insert(l.getFecha(), l.getIdOrdenFk(), l.getIdEntradaMateriaPrimaFK(), l.getCantidad(), l.getPesoNeto(), l.getObservaciones(), l.getIdTipoProductoFk(), l.getIdUsuarioFk());

    }

    @Transactional
    public int delete(Lavado l) {
        return lavadoRepository.delete(l.getIdLavadoPk());

    }

    public List<Lavado> getByIdOrden(Integer idOrdenTrabajo,Integer idTipoProducto) {
        List<Lavado> lstL = new ArrayList<>();
        List<Object[]> lstObject = lavadoRepository.getByIdOrden(idOrdenTrabajo,idTipoProducto);
        lstObject.forEach(o -> {

            Lavado l = new Lavado();

            l.setIdLavadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            l.setFecha(o[1] == null ? null : (Date) o[1]);
            l.setIdOrdenFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            l.setIdEntradaMateriaPrimaFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            l.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            l.setPesoNeto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            l.setObservaciones(o[6] == null ? "" : o[6].toString());
            l.setIdTipoProductoFk(o[7] == null ? null : Integer.valueOf(o[7].toString()));
            l.setEstatus(o[8] == null ? null : o[8].toString());
            l.setLoteMateriaPrima(o[9] == null ? null : o[9].toString());
            l.setDescripcionTipoProducto(o[10] == null ? null : o[10].toString());
            System.out.println("add " + l.toString());
            lstL.add(l);
        });

        return lstL;
    }

    @Transactional
    public int empacar(Integer idLavado) {
        return lavadoRepository.empacar(idLavado);

    }

    public List<Lavado> getEmpacadoByIdOrdenEntradaAndFechas(Integer idOrdenTrabajo, Integer idEntrada, String fechaInicio, String fechaFin) {
        List<Lavado> lstL = new ArrayList<>();
        List<Object[]> lstObject = lavadoRepository.getEmpacadoByIdOrdenEntradaAndFechas(idOrdenTrabajo, idEntrada, fechaInicio, fechaFin);
        lstObject.forEach(o -> {

            Lavado l = new Lavado();

            l.setIdLavadoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            l.setFecha(o[1] == null ? null : (Date) o[1]);
            l.setIdOrdenFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            l.setIdEntradaMateriaPrimaFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            l.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            l.setPesoNeto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            l.setObservaciones(o[6] == null ? "" : o[6].toString());
            l.setIdTipoProductoFk(o[7] == null ? null : Integer.valueOf(o[7].toString()));
            l.setIdUsuarioFk(o[8] == null ? null : Integer.valueOf(o[8].toString()));
            l.setLoteMateriaPrima(o[9] == null ? null : o[9].toString());
            l.setNumeroOrdenTrabajo(o[10] == null ? null : o[10].toString());
            l.setDescripcionTipoProducto(o[11] == null ? null : o[11].toString());
            l.setNombreUsuario(o[12] == null ? null : o[12].toString());
            l.setEstatus(o[13] == null ? null : o[13].toString());
            l.setFechaEmpacado(o[14] == null ? null : (Date) o[14]);

            lstL.add(l);
        });

        return lstL;
    }

}
