package com.fresh.service;

import com.fresh.dto.SalidaProducto;
import com.fresh.repository.SalidaProductoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalidaProductoService {

    @Autowired
    private SalidaProductoRepository salidaProductoRepository;

    @Transactional
    public int delete(Integer idSalidaProducto) {
        return salidaProductoRepository.delete(idSalidaProducto);
    }

    @Transactional
    public int insert(SalidaProducto sp) {
        int insert = salidaProductoRepository.insert(sp.getIdSalidaFk(), sp.getIdEnvasadoFk(), sp.getCantidad(), sp.getKilos(), sp.getKilosBruto(), sp.getTara(), sp.getObservaciones());
        if (insert != 0) {///si se inserta se regresa el id
            return getLastInsertId();
        }

        return 0;
    }

    public ArrayList<SalidaProducto> getByIdSalidaAndEstatus(Integer idSalida, String estatus) {
        ArrayList<SalidaProducto> lstSalidaProducto = new ArrayList<SalidaProducto>();
        List<Object[]> lstObject = salidaProductoRepository.getByIdSalidaAndEstatus(idSalida, estatus);
        for (Object[] o : lstObject) {
            SalidaProducto s = new SalidaProducto();
            s.setIdSalidaProductoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            s.setIdSalidaFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            s.setIdEnvasadoFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            s.setCantidad(o[3] == null ? null : new BigDecimal(o[3].toString()));
            s.setKilos(o[4] == null ? null : new BigDecimal(o[4].toString()));
            s.setKilosBruto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            s.setTara(o[6] == null ? null : new BigDecimal(o[6].toString()));
            s.setEstatus(o[7] == null ? null : o[7].toString());
            s.setObservaciones(o[8] == null ? null : o[8].toString());
            s.setIdUsuarioCancelaFk(o[9] == null ? null : Integer.valueOf(o[9].toString()));
            s.setFechaCancelacion(o[10] == null ? null : (Date) o[10]);
            s.setIdOrdenFk(o[11] == null ? null : Integer.valueOf(o[11].toString()));
            s.setIdEntradaMateriaPrimaFk(o[12] == null ? null : Integer.valueOf(o[12].toString()));
            s.setNumeroTarima(o[13] == null ? null : Integer.valueOf(o[13].toString()));
            s.setTamano(o[14] == null ? null : o[14].toString());
            s.setTipoProductoDescripcion(o[16] == null ? null : o[16].toString());

            lstSalidaProducto.add(s);
        }

        return lstSalidaProducto;
    }

    public SalidaProducto getByIdSalidaProducto(Integer idSalidaProducto) {
        SalidaProducto s = new SalidaProducto();
        List<Object[]> lstObject = salidaProductoRepository.getByIdSalidaProducto(idSalidaProducto);
        for (Object[] o : lstObject) {
            s.setIdSalidaProductoPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            s.setIdSalidaFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            s.setIdEnvasadoFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            s.setCantidad(o[3] == null ? null : new BigDecimal(o[3].toString()));
            s.setKilos(o[4] == null ? null : new BigDecimal(o[4].toString()));
            s.setKilosBruto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            s.setTara(o[6] == null ? null : new BigDecimal(o[6].toString()));
            s.setEstatus(o[7] == null ? "1" : o[7].toString());
            s.setObservaciones(o[8] == null ? null : o[8].toString());
            s.setIdUsuarioCancelaFk(o[9] == null ? null : Integer.valueOf(o[9].toString()));
            s.setFechaCancelacion(o[10] == null ? null : (Date) o[10]);
            s.setIdOrdenFk(o[11] == null ? null : Integer.valueOf(o[11].toString()));
            s.setIdEntradaMateriaPrimaFk(o[12] == null ? null : Integer.valueOf(o[12].toString()));
            s.setNumeroTarima(o[13] == null ? null : Integer.valueOf(o[13].toString()));
            s.setTamano(o[14] == null ? null : o[14].toString());
            s.setTipoProductoDescripcion(o[16] == null ? null : o[16].toString());
        }

        return s;
    }

    public int getLastInsertId() {
        return salidaProductoRepository.getLastInsertId();
    }

}
