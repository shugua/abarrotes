package com.fresh.service;

import com.fresh.dominio.AccesMenu;
import com.fresh.dominio.Rol;
import com.fresh.repository.UsuarioRepository;
import com.fresh.dominio.Usuario;
import com.fresh.dto.RolD;
import com.fresh.dto.UsuarioD;
import com.fresh.repository.AccesMenuRepository;
import com.fresh.repository.RolRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class RolService implements Serializable {
    
    @Autowired
    RolRepository rolRepository;
    
    public ArrayList<RolD> findAll() {
        
        List<Rol> lst = rolRepository.findAll();
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }
        
        return (ArrayList<RolD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
        
    }
    
    private RolD convertirEntidadADTO(Rol r) {
        RolD dto = new RolD();
        dto.setIdRolPk(r.getIdRolPk());
        dto.setNombreRol(r.getNombreRol());
        dto.setDescripcionRol(r.getDescripcionRol());
        return dto;
    }
    
}
