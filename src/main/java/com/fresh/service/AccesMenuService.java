package com.fresh.service;

import com.fresh.dominio.Rol;
import com.fresh.dto.RolD;
import com.fresh.repository.AccesMenuRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class AccesMenuService implements Serializable {
    @Autowired
    AccesMenuRepository accesMenuRepository;



    private RolD convertirEntidadADTO(Rol a) {
        RolD dto = new RolD();
        


        return dto;
    }

}
