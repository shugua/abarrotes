package com.fresh.service;

import com.fresh.dto.MateriaPrimaProductoD;
import com.fresh.dto.OrdenTrabajoProductoD;
import com.fresh.repository.OrdenTrabajoProductoRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class OrdenTrabajoProductoService implements Serializable {

    @Autowired
    OrdenTrabajoProductoRepository ordenTrabajoProductoRepository;

    @Transactional
    public int delete(Integer id) {
        return ordenTrabajoProductoRepository.delete(id);
    }

    @Transactional
    public int update(OrdenTrabajoProductoD o) {
        return ordenTrabajoProductoRepository.update(o.getCantidad(), o.getTipo(), o.getIdOrdenTrabajoProducto());
    }

    @Transactional
    public int insert(OrdenTrabajoProductoD o) {
        return ordenTrabajoProductoRepository.insert(o.getCantidad(), o.getTipo(), o.getIdOrdenFk(), o.getLotePT());
    }

    public ArrayList<OrdenTrabajoProductoD> getByIdOrdenTrabajoFK(Integer idOrdenTrabajo) {
        ArrayList<OrdenTrabajoProductoD> lstO = new ArrayList<>();
        List<Object[]> lstObject = ordenTrabajoProductoRepository.getByIdOrdenTrabajoFK(idOrdenTrabajo);
        lstObject.forEach(o -> {

            OrdenTrabajoProductoD oT = new OrdenTrabajoProductoD();
            oT.setIdOrdenTrabajoProducto(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            oT.setCantidad(o[1] == null ? null : new BigDecimal(o[1].toString()));
            oT.setTipo(o[2] == null ? null : o[2].toString());
            oT.setIdOrdenFk(o[3] == null ? null : Integer.valueOf(o[3].toString()));
            oT.setEstatus(o[4] == null ? null : o[4].toString());
            oT.setLotePT(o[5] == null ? null : o[5].toString());

            lstO.add(oT);
        });

        return lstO;
    }

}
