package com.fresh.controller;

import com.fresh.dto.UsuarioD;
import com.fresh.service.UserService;
import com.fresh.service.DynamicMenuService;
import com.fresh.service.MenuService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan de la Cruz
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InfoUsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(InfoUsuarioController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DynamicMenuService dynamicMenuService;
    private UsuarioD usuario;
    private MenuModel menuModel;

    @PostConstruct
    public void init() {

        usuario = userService.getUsuarioAutenticadoMenu();
        if (usuario != null && usuario.getId() != null) {
            menuModel = dynamicMenuService.getMenuModel(usuario);
            if (!usuario.getMenu().isEmpty()) {
                usuario.setAllowedUrl(menuService.getAllowedUrl(usuario.getRol().getIdRolPk()));

            }
        }

    }

    public UsuarioD getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioD usuario) {
        this.usuario = usuario;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

}
