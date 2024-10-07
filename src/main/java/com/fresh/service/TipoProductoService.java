package com.fresh.service;

import com.fresh.dominio.TipoTarima;
import com.fresh.dto.TipoProductoD;
import com.fresh.dto.TipoTarimaD;
import com.fresh.repository.TipoProductoRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class TipoProductoService implements Serializable {
    
    @Autowired
    TipoProductoRepository tipoProductoRepository;
    
    public ArrayList<TipoProductoD> findAll() {
        
        ArrayList<TipoProductoD> lstT = new ArrayList<>();
        List<Object[]> lst = tipoProductoRepository.findAllD();
        
        for (Object[] o : lst) {
            TipoProductoD t = new TipoProductoD();
            t.setIdTipoProductoPk(o[0] == null ? 0 : Integer.parseInt(o[0].toString()));
            t.setDescripcion(o[1] == null ? "" : o[1].toString());
            t.setPeso(o[2] == null ? BigDecimal.ZERO : new BigDecimal(o[2].toString()));
            lstT.add(t);
        }
        
        return lstT;
        
    }
    
}
