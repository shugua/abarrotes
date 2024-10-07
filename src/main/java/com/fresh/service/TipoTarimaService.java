package com.fresh.service;

import com.fresh.dominio.TipoTarima;
import com.fresh.dto.TipoTarimaD;
import com.fresh.repository.TipoTarimaRepository;
import java.io.Serializable;
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
public class TipoTarimaService implements Serializable {
    
    @Autowired
    TipoTarimaRepository tipoTarimaRepository;
    
    public ArrayList<TipoTarimaD> findAll() {
        
        List<TipoTarima> lst = tipoTarimaRepository.findAll();
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }
        
        return (ArrayList<TipoTarimaD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
        
    }
    
    private TipoTarimaD convertirEntidadADTO(TipoTarima t) {
        TipoTarimaD dto = new TipoTarimaD();
        dto.setIdTarimaPk(t.getIdTarimaPk());
        dto.setNombre(t.getNombre());
        dto.setPeso(t.getPeso());
        dto.setEstatus(t.getEstatus());
        return dto;
    }
    
}
