package com.fresh.service;

import com.fresh.dominio.TipoPresentacion;
import com.fresh.dto.TipoPresentacionD;
import com.fresh.repository.RolRepository;
import com.fresh.repository.TipoPresentacionRepository;
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
public class TipoPresentacionService implements Serializable {
    
    @Autowired
    TipoPresentacionRepository tipoPresentacionRepository;
    
    public ArrayList<TipoPresentacionD> findAll() {
        
        List<TipoPresentacion> lst = tipoPresentacionRepository.findAll();
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }
        
        return (ArrayList<TipoPresentacionD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
        
    }
    
    private TipoPresentacionD convertirEntidadADTO(TipoPresentacion t) {
        TipoPresentacionD dto = new TipoPresentacionD();
        dto.setIdPresentacionPk(t.getIdPresentacionPk());
        dto.setNombre(t.getNombre());
        dto.setPeso(t.getPeso());
        dto.setEstatus(t.getEstatus());
        return dto;
    }
    
}
