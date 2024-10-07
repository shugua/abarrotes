package com.fresh.service;

import com.fresh.dto.OrdenTrabajoLote;
import com.fresh.repository.OrdenTrabajoLoteRepository;
import java.io.Serializable;
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
public class OrdenTrabajoLoteService implements Serializable {

    @Autowired
    OrdenTrabajoLoteRepository ordenTrabajoLoteRepository;

    public ArrayList<OrdenTrabajoLote> getByIdOrden(Integer idOrden) {
        ArrayList<OrdenTrabajoLote> lstOrdenTrabajoLote = new ArrayList<>();
        List<Object[]> lstObject = ordenTrabajoLoteRepository.getByIdOrden(idOrden);

        lstObject.forEach(o -> {
            OrdenTrabajoLote oR = new OrdenTrabajoLote();
            oR.setIdEntradaMateriaPrimaFk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            oR.setIdOrdenTrabajoFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));
            oR.setLote(o[2] == null ? "" : o[2].toString());
            oR.setNombreCliente(o[3] == null ? "" : o[3].toString());
            oR.setNombreProveedor(o[4] == null ? "" : o[4].toString());

            lstOrdenTrabajoLote.add(oR);
        });

        return lstOrdenTrabajoLote;
    }
    
     public ArrayList<OrdenTrabajoLote> getByIdOrdenAndIdEntrada(Integer idOrden,Integer idEntrada) {
        ArrayList<OrdenTrabajoLote> lstOrdenTrabajoLote = new ArrayList<>();
        List<Object[]> lstObject = ordenTrabajoLoteRepository.getByIdOrdenAndIdEntrada(idOrden,idEntrada);

        lstObject.forEach(o -> {
            OrdenTrabajoLote oR = new OrdenTrabajoLote();
            oR.setIdEntradaMateriaPrimaFk(o[0] == null ? null : Integer.valueOf(o[0].toString()));
            oR.setIdOrdenTrabajoFk(o[1] == null ? null : Integer.valueOf(o[1].toString()));

            lstOrdenTrabajoLote.add(oR);
        });

        return lstOrdenTrabajoLote;
    }

    @Transactional
    public int insert(OrdenTrabajoLote oTL) {
        return ordenTrabajoLoteRepository.insert(oTL.getIdEntradaMateriaPrimaFk(), oTL.getIdOrdenTrabajoFk());

    }

    @Transactional
    public int delete(OrdenTrabajoLote oTL) {
        return ordenTrabajoLoteRepository.delete(oTL.getIdEntradaMateriaPrimaFk(), oTL.getIdOrdenTrabajoFk());

    }

}
