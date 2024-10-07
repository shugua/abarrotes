package com.fresh.service;

import com.fresh.dominio.Producto;
import com.fresh.dto.MateriaPrimaProductoD;
import com.fresh.dto.ProductoD;
import com.fresh.repository.EntradaMateriaPrimaProductoRepository;
import com.fresh.repository.ProductoRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class EntradaMateriaPrimaProductoService implements Serializable {

    @Autowired
    EntradaMateriaPrimaProductoRepository entradaMateriaPrimaProductoRepository;

    @Transactional
    public int delete(Integer id) {
        return entradaMateriaPrimaProductoRepository.delete(id);
    }

    @Transactional
    public int update(MateriaPrimaProductoD mP) {
        return entradaMateriaPrimaProductoRepository.update(mP.getCantidad(), mP.getPesoBruto(), mP.getPesoNeto(), mP.getPesoTarima(), mP.getPesoPresentacion(), mP.getIdTarima(), mP.getIdPresentacion(), mP.getObservaciones(), mP.getIdMateriaPrimaProductoPK());
    }

    @Transactional
    public int insert(MateriaPrimaProductoD p) {

        return entradaMateriaPrimaProductoRepository.insert(p.getIdEntradaMateriaPrimaFK(), p.getNumeroTarima(), p.getIdProductoFK(), p.getCantidad(), p.getPesoBruto(), p.getPesoNeto(), p.getPesoTarima(), p.getPesoPresentacion(), p.getIdTarima(), p.getIdPresentacion(), p.getObservaciones());
    }
    
     @Transactional
    public int pelar(MateriaPrimaProductoD mP) {
        return entradaMateriaPrimaProductoRepository.pelar(mP.getFechaPelado(),mP.getIdOrdenFk() ,mP.getIdMateriaPrimaProductoPK());
    }

    public List<MateriaPrimaProductoD> getByIdEntradaPk(Integer idEntradaMateriaPrimaPk) {
        List<MateriaPrimaProductoD> lstM = new ArrayList<>();
        List<Object[]> lstObject = entradaMateriaPrimaProductoRepository.getByIdEntradaPk(idEntradaMateriaPrimaPk);
        lstObject.forEach(o -> {

            MateriaPrimaProductoD m = new MateriaPrimaProductoD();
            m.setIdMateriaPrimaProductoPK(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            m.setIdEntradaMateriaPrimaFK(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            m.setNumeroTarima(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            m.setIdProductoFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            m.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            m.setPesoBruto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            m.setPesoNeto(o[6] == null ? null : new BigDecimal(o[6].toString()));
            m.setIdTarima(o[7] == null ? null : Integer.valueOf(o[7].toString()));
            m.setIdPresentacion(o[8] == null ? null : Integer.valueOf(o[8].toString()));
            m.setEstatus(o[9] == null ? null : o[9].toString());
            m.setObservaciones(o[10] == null ? null : o[10].toString());
            m.setNombreProducto(o[11] == null ? null : o[11].toString());
            m.setNombreTarima(o[12] == null ? null : o[12].toString());
            m.setNombrePresentacion(o[13] == null ? null : o[13].toString());
            m.setPesoPresentacion(o[14] == null ? null : new BigDecimal(o[14].toString()));
            m.setPesoTarima(o[15] == null ? null : new BigDecimal(o[15].toString()));

            lstM.add(m);
        });

        return lstM;
    }

    public List<MateriaPrimaProductoD> getByIdOrdenEntradaAndFechas(Integer idOrdenTrabajo, String fechaInicio, String fechaFin) {
        List<MateriaPrimaProductoD> lstM = new ArrayList<>();
        List<Object[]> lstObject = entradaMateriaPrimaProductoRepository.getByIdOrdenEntradaAndFechas(idOrdenTrabajo, fechaInicio, fechaFin);
        lstObject.forEach(o -> {

            MateriaPrimaProductoD m = new MateriaPrimaProductoD();
            m.setIdMateriaPrimaProductoPK(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            m.setIdEntradaMateriaPrimaFK(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            m.setNumeroTarima(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            m.setIdProductoFK(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            m.setCantidad(o[4] == null ? null : new BigDecimal(o[4].toString()));
            m.setPesoBruto(o[5] == null ? null : new BigDecimal(o[5].toString()));
            m.setPesoNeto(o[6] == null ? null : new BigDecimal(o[6].toString()));
            m.setIdTarima(o[7] == null ? null : Integer.valueOf(o[7].toString()));
            m.setIdPresentacion(o[8] == null ? null : Integer.valueOf(o[8].toString()));
            m.setEstatus(o[9] == null ? null : o[9].toString());
            m.setObservaciones(o[10] == null ? null : o[10].toString());
            m.setNombreProducto(o[11] == null ? null : o[11].toString());
            m.setNombreTarima(o[12] == null ? null : o[12].toString());
            m.setNombrePresentacion(o[13] == null ? null : o[13].toString());
            m.setPesoPresentacion(o[14] == null ? null : new BigDecimal(o[14].toString()));
            m.setPesoTarima(o[15] == null ? null : new BigDecimal(o[15].toString()));
            m.setLoteMateriaPrima(o[16] == null ? null : o[16].toString());
            m.setOrdenTrabajo(o[17] == null ? null : o[17].toString());
            m.setFechaPelado(o[18] == null ? null : (Date) o[18]);

            lstM.add(m);
        });

        return lstM;
    }

//    private MateriaPrimaProductoD convertirEntidadADTO(Producto p) {
//        MateriaPrimaProductoD dto = new MateriaPrimaProductoD();
//        dto.setIdProductoPk(p.getIdProductoPk());
//        dto.setNombre(p.getNombre());
//        dto.setMinimo(p.getMinimo());
//        dto.setTiempoEntrega(p.getTiempoEntrega());
//        dto.setTipo(p.getTipo());
//        dto.setEstatus(p.getEstatus());
//        dto.setCodigo(p.getCodigo());
//        dto.setClaveUnidad(p.getClaveUnidad());
//        dto.setClaveProductoServicio(p.getClaveProductoServicio());
//
//        return dto;
//    }
}
