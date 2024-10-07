package com.fresh.controller;

import com.fresh.dto.RolD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.RolService;
import com.fresh.service.UserService;
import com.fresh.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Juan de la Cruz
 */
@Component
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private RolService rolService;
    @Autowired
    private UserService userService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ArrayList<RolD> lstRol;
    private ArrayList<UsuarioD> model;
    private UsuarioD data;

    private UsuarioD usuario;

    private String stateView;

    @PostConstruct
    public void init() {

        stateView = "init";
        buscaUsuario();
        usuario = infoUsuarioController.getUsuario();
        buscarRoles();
    }

    private void buscarRoles() {
        lstRol = rolService.findAll();
    }

    public void viewNew() {
        stateView = "new";
        data = new UsuarioD();
    }

    public String save() {
        if (data.getId() == null) {
            insert();
            JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
            return "usuario.xhtml";
        } else {
            update();
            JsfUtil.addSuccessMessage("Registro Modificado Correctamente.");
            return "usuario.xhtml";
        }

    }

    public int insert() {
        data.setEstatus("1");
        data.setPassword(passwordEncoder.encode(data.getPassword()));
        data.setUsuarioAltaFk(usuario.getId());
        return userService.insert(data);
    }

    public int update() {
        return userService.update(data);
    }

    public void searchById() {
        stateView = "edit";
        ///volvemos a realizar busqueda del usuario
        data = userService.findByUsuarioPk(data.getId());
    }

    public String detele() {
        if (data.getEstatus().endsWith("1")) {
            userService.delete(data.getId(), usuario.getId());
            JsfUtil.addSuccessMessage("Registro Eliminado Correctamente.");
        } else {
            userService.activar(data.getId());
            JsfUtil.addSuccessMessage("Registro Activado Correctamente.");
        }

        return "usuario.xhtml";

    }

    public String activar() {
        userService.activar(data.getId());
        JsfUtil.addSuccessMessage("Registro Activado Correctamente.");
        return "usuario.xhtml";

    }

    public void back() {
        data = new UsuarioD();
        stateView = "init";
    }

    private void buscaUsuario() {
        model = userService.findAll();
    }

    public UsuarioD getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioD usuario) {
        this.usuario = usuario;
    }

    public String getStateView() {
        return stateView;
    }

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

    public ArrayList<UsuarioD> getModel() {
        return model;
    }

    public void setModel(ArrayList<UsuarioD> model) {
        this.model = model;
    }

    public UsuarioD getData() {
        return data;
    }

    public void setData(UsuarioD data) {
        this.data = data;
    }

    public ArrayList<RolD> getLstRol() {
        return lstRol;
    }

    public void setLstRol(ArrayList<RolD> lstRol) {
        this.lstRol = lstRol;
    }

}
