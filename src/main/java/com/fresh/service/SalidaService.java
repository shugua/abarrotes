package com.fresh.service;

import com.fresh.dto.Lavado;
import com.fresh.dto.Salida;
import com.fresh.repository.SalidaRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class SalidaService implements Serializable {

    @Autowired
    SalidaRepository salidaRepository;

    @Autowired
    SalidaProductoService salidaProductoService;

    public Salida getByIdSalida(Integer idSalida) {
        List<Object[]> lstObject = salidaRepository.getByIdSalida(idSalida);

        Salida s = new Salida();
        lstObject.forEach(o -> {

            s.setIdSalidaPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            s.setFecha(o[1] == null ? null : (Date) o[1]);
            s.setIdCliente(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            s.setDatoCamion(o[3] == null ? null : o[3].toString());
            s.setChofer(o[4] == null ? null : o[4].toString());
            s.setIdUsuario(o[5] == null ? null : Integer.valueOf(o[5].toString()));
            s.setEstatus(o[6] == null ? "" : o[6].toString());
            s.setNombreCliente(o[7] == null ? null : o[7].toString());
            s.setNombreUsuario(o[8] == null ? null : o[8].toString());
            s.setKilos(o[9] == null ? null : new BigDecimal(o[9].toString()));
            s.setCantidad(o[10] == null ? null : new BigDecimal(o[10].toString()));
            s.setLstSalidaProducto(salidaProductoService.getByIdSalidaAndEstatus(s.getIdSalidaPk(), "1"));

        });

        return s;
    }

    public List<Salida> getByIdOrdenEntradaAndFechas(Integer idOrdenTrabajo, Integer idEntrada, String fechaInicio, String fechaFin, String estatus) {
        List<Salida> lstL = new ArrayList<>();
        List<Object[]> lstObject = salidaRepository.getByIdOrdenEntradaAndFechas(idOrdenTrabajo, idEntrada, fechaInicio, fechaFin, estatus);
        lstObject.forEach(o -> {

            Salida s = new Salida();

            s.setIdSalidaPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            s.setFecha(o[1] == null ? null : (Date) o[1]);            
            s.setChofer(o[2] == null ? null : o[2].toString());
            s.setIdUsuario(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            s.setEstatus(o[4] == null ? "" : o[4].toString());
            s.setNombreCliente(o[5] == null ? null : o[5].toString());
            s.setNombreUsuario(o[6] == null ? null : o[6].toString());
            s.setCantidad(o[7] == null ? BigDecimal.ZERO : new BigDecimal(o[7].toString()));
            s.setKilos(o[8] == null ? BigDecimal.ZERO : new BigDecimal(o[8].toString()).setScale(2,RoundingMode.HALF_UP));
            s.setKilosBruto(o[9] == null ? BigDecimal.ZERO : new BigDecimal(o[9].toString()).setScale(2,RoundingMode.HALF_UP));
            s.setIdOrdenFk(o[10] == null ? null : Integer.valueOf(o[10].toString()));
            s.setIdCliente(o[11] == null ? null : Integer.valueOf(o[11].toString()));
            s.setNumeroOrden(o[12] == null ? null : o[12].toString());
            
            s.setLstSalidaProducto(salidaProductoService.getByIdSalidaAndEstatus(s.getIdSalidaPk(), "1"));
            lstL.add(s);
        });
        return lstL;
    }

    @Transactional
    public int insert(Salida s) {

        int insert = salidaRepository.insert(s.getFecha(), s.getIdCliente(), s.getDatoCamion(), s.getChofer(), s.getObservaciones(), s.getIdUsuario(), s.getTract(), s.getPlate(), s.getBox(), s.getPlateBox(), s.getSeal(), s.getTemperature(), s.getDeviceId());
        return insert == 0 ? 0 : getLastInsertId();////si se inserta el registro se regresa el ultimo id insertado

    }

    @Transactional
    public int delete(Salida s) {
        return salidaRepository.delete(s.getIdSalidaPk());

    }

    public Integer getLastInsertId() {
        return salidaRepository.getLastInsertId();
    }

}
