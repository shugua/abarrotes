package com.fresh.service;

import com.fresh.repository.UsuarioRepository;
import com.fresh.dominio.Usuario;
import com.fresh.dto.MenuD;
import com.fresh.dto.RolD;
import com.fresh.dto.UsuarioD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class UserService implements Serializable, UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        System.out.println("user.getUsername() "+user.getUsername());

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getRoles(user)) // Reemplaza getRoles con la lógica adecuada
                .build();

    }

  
    public UsuarioD getUsuarioAutenticadoMenu() {
    UserDetails usuarioActual = null;
    UsuarioD userD = null;
    Usuario user = null;
    SecurityContext context = SecurityContextHolder.getContext();
    
    if (context != null) {
        Authentication auth = context.getAuthentication();
        if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
            usuarioActual = (UserDetails) auth.getPrincipal();
            System.out.println("Usuario autenticado: " + usuarioActual.getUsername());

            // Volvemos a realizar la búsqueda del usuario por la clave del usuario
            user = usuarioRepository.findByUsername(usuarioActual.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado. "));
            System.out.println("Usuario encontrado: " + user.getUsername());
        } else {
            System.out.println("Autenticación no válida o usuario anónimo.");
        }
    } else {
        System.out.println("SecurityContext es nulo.");
    }

    if (user != null && user.getId() != null && user.getIdRolFk() != null) {
        userD = convertirEntidadADTO(user);

        // Seteamos el menú
        userD.setMenu(menuService.ConstuctMenu(user.getIdRolFk().getIdRolPk()));
        // Seteamos Rol
        userD.setRol(new RolD(user.getIdRolFk().getIdRolPk(), user.getIdRolFk().getNombreRol(), user.getIdRolFk().getDescripcionRol()));
    }

    return userD;
}
    

    public UsuarioD getUsuarioAutenticado() {
        UserDetails usuarioActual = null;
        UsuarioD userD = null;
        Usuario user = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication auth = context.getAuthentication();
            if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
                usuarioActual = (UserDetails) auth.getPrincipal();

////volvemos a realizar la busqueda del usuario por la clave del usuario
                user = usuarioRepository.findByUsername(usuarioActual.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado. "));

            }
        }

        if (user != null && user.getId() != null) {
            System.out.println("user "+user);
            userD = convertirEntidadADTO(user);
        }

        return userD;
    }

    private String[] getRoles(Usuario user) {

        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        System.out.println("rol");
        return user.getRole().split(",");
    }

    private UsuarioD convertirEntidadADTO(Usuario u) {
        UsuarioD dto = new UsuarioD();
        System.out.println("u "+u);
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setNombre(u.getNombre());
        dto.setPassword(u.getPassword());
        dto.setTelefono(u.getTelefono());
        dto.setEstatus(u.getEstatus());
        dto.setIdRolFk(u.getIdRolFk().getIdRolPk());

        return dto;
    }

    public ArrayList<UsuarioD> findAll() {
        List<Usuario> lst = usuarioRepository.findAll();

        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<UsuarioD>) lst.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

    }

    public UsuarioD findByUsuarioPk(Integer idUsuarioPk) {
        UsuarioD userD = new UsuarioD();
        Usuario usuario = usuarioRepository.findByIdUsuarioPk(idUsuarioPk);
        userD = convertirEntidadADTO(usuario);
        userD.setIdRolFk(usuario.getIdRolFk().getIdRolPk());
        return userD;

    }

    @Transactional
    public int delete(Integer idUsuarioPk, Integer idUsuario) {
        return usuarioRepository.deleteByIdUsuarioPk(idUsuarioPk, idUsuario);
    }

    @Transactional
    public int activar(Integer idUsuarioPk) {
        return usuarioRepository.activarByIdUsuarioPk(idUsuarioPk);
    }

    @Transactional
    public int update(UsuarioD u) {

        return usuarioRepository.update(u.getUsername(), u.getPassword(), u.getNombre(), u.getTelefono(), u.getIdRolFk(), u.getId());

    }

    @Transactional
    public int insert(UsuarioD u) {

        return usuarioRepository.insert(u.getUsername(), u.getPassword(), u.getNombre(), u.getTelefono(), u.getIdRolFk(), u.getEstatus(), u.getUsuarioAltaFk());
    }

}
