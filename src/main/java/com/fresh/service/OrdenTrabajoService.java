package com.fresh.service;

import com.fresh.dominio.OrdenTrabajo;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.repository.OrdenTrabajoRepository;
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
public class OrdenTrabajoService implements Serializable {

    @Autowired
    OrdenTrabajoRepository ordenTrabajoRepository;
    @Autowired
    private OrdenTrabajoProductoService ordenTrabajoProductoService;
    @Autowired
    private OrdenTrabajoLoteService ordenTrabajoLoteService;

    public List<OrdenTrabajoD> findByFechaAndIdProveedorAndIdCliente(Integer idProveedor, Integer idCliente, String fechaInicio, String fechaFin) {
        List<OrdenTrabajoD> lstOrdenTrabajo = new ArrayList<OrdenTrabajoD>();
        List<Object[]> lstO = ordenTrabajoRepository.findByFechaAndIdProveedorAndIdCliente(fechaInicio, fechaFin, idProveedor, idCliente);
        lstO.forEach(o -> {

            OrdenTrabajoD oT = new OrdenTrabajoD();
            oT.setIdOrdenPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            oT.setNumeroOrden(o[1] == null ? null : o[1].toString());
//            oT.setIdEntradaMateriaPrimaFk(o[2] == null ? null : Integer.valueOf(o[2].toString()));
            oT.setFechaEntrega(o[2] == null ? null : (Date) o[2]);
            oT.setLotePT(o[3] == null ? null : o[3].toString());
            oT.setObservaciones(o[4] == null ? null : o[4].toString());
            oT.setIdUsuarioFk(o[5] == null ? null : Integer.valueOf(o[5].toString()));
            oT.setFecha(o[6] == null ? null : (Date) o[6]);
            oT.setEstatus(o[7] == null ? null : o[7].toString());
            oT.setCantidad(o[8] == null ? BigDecimal.ZERO : new BigDecimal(o[8].toString()));

            oT.setLstOrdenTrabajoProducto(ordenTrabajoProductoService.getByIdOrdenTrabajoFK(oT.getIdOrdenPk()));
            oT.setLstOrdenTrabajoLote(ordenTrabajoLoteService.getByIdOrden(oT.getIdOrdenPk()));

            oT.setLote("");
            oT.setNombreCliente("");
            oT.setNombreProveedor("");
            if (oT.getLstOrdenTrabajoLote() != null && !oT.getLstOrdenTrabajoLote().isEmpty()) {
                oT.setLote(oT.getLstOrdenTrabajoLote().get(0).getLote());
                oT.setNombreCliente(oT.getLstOrdenTrabajoLote().get(0).getNombreCliente());
                oT.setNombreProveedor(oT.getLstOrdenTrabajoLote().get(0).getNombreProveedor());
            }

            lstOrdenTrabajo.add(oT);

        });

        return lstOrdenTrabajo;

    }

    public OrdenTrabajoD findByIdOrdenPK(Integer idOrdenTrabajo) {
        List<Object[]> lstO = ordenTrabajoRepository.findByIdOrdenPK(idOrdenTrabajo);
        OrdenTrabajoD oT = new OrdenTrabajoD();
        lstO.forEach(o -> {

            oT.setIdOrdenPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            oT.setNumeroOrden(o[1] == null ? null : o[1].toString());
            oT.setFechaEntrega(o[2] == null ? null : (Date) o[2]);
            oT.setObservaciones(o[3] == null ? null : o[3].toString());
            oT.setIdUsuarioFk(o[4] == null ? null : Integer.valueOf(o[4].toString()));
            oT.setFecha(o[5] == null ? null : (Date) o[5]);
            oT.setEstatus(o[6] == null ? null : o[6].toString());

        });
        System.out.println("return");
        return oT;

    }

    public ArrayList<OrdenTrabajoD> findByOrdenTrabajo(String numeroOrden) {
        List<Object[]> lstO = ordenTrabajoRepository.findByOrdenTrabajo(numeroOrden);
        ArrayList<OrdenTrabajoD> lstOrden = new ArrayList<>();

        lstO.forEach(o -> {
            OrdenTrabajoD oT = new OrdenTrabajoD();

            oT.setIdOrdenPk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            oT.setNumeroOrden(o[1] == null ? null : o[1].toString());
            oT.setFechaEntrega(o[2] == null ? null : (Date) o[2]);
            oT.setObservaciones(o[3] == null ? null : o[3].toString());
            oT.setIdUsuarioFk(o[4] == null ? null : Integer.valueOf(o[4].toString()));
            oT.setFecha(o[5] == null ? null : (Date) o[5]);
            oT.setEstatus(o[6] == null ? null : o[6].toString());

            lstOrden.add(oT);

        });
        System.out.println("lstOrden "+lstOrden);
        return lstOrden;

    }

    @Transactional
    public int delete(Integer idEntradaMateriaPrimaPk, Integer idUsuario) {
        return ordenTrabajoRepository.delete(idEntradaMateriaPrimaPk);
    }

    @Transactional
    public int update(OrdenTrabajoD o) {
        return ordenTrabajoRepository.update(o.getNumeroOrden(), o.getFechaEntrega(), o.getLotePT(), o.getObservaciones(), o.getIdOrdenPk());
    }

    @Transactional
    public int insert(OrdenTrabajoD d) {
        OrdenTrabajo e = convertirDTOAEntidad(d);
        return ordenTrabajoRepository.save(e).getIdOrdenPk();
    }

    private OrdenTrabajo convertirDTOAEntidad(OrdenTrabajoD d) {
        OrdenTrabajo o = new OrdenTrabajo();
        o.setNumeroOrden(d.getNumeroOrden());
        o.setFecha(d.getFecha());
        o.setFechaEntrega(d.getFechaEntrega());
        o.setLotePt(d.getLotePT());
        o.setObservaciones(d.getObservaciones());
        o.setEstatus('1');
//        o.setIdEntradaMateriaPrimaFk(d.getIdEntradaMateriaPrimaFk());
        o.setIdUsuarioFk(d.getIdUsuarioFk());
        return o;
    }

}
