package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.UserService;
import com.fresh.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan de la Cruz
 */
@Component
@ViewScoped
@Scope("view")
public class ClienteController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private ClienteService clienteService;

    private ArrayList<ClienteD> model;
    private ClienteD data;
    private UsuarioD usuario;

    private String stateView;

    @PostConstruct
    public void init() {

        stateView = "init";
        buscaCliente();
        usuario = infoUsuarioController.getUsuario();
    }

    public void viewNew() {
        stateView = "new";
        data = new ClienteD();
    }

    public String save() {
        if (data.getIdClientePk() == null) {
            insert();
            JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
            return "clientes.xhtml";
        } else {
            update();
            JsfUtil.addSuccessMessage("Registro Modificado Correctamente.");
            return "clientes.xhtml";
        }

    }

    public int insert() {
        data.setEstatus("1");
        data.setIdUsuarioAltaPk(usuario.getId());
        return clienteService.insert(data);
    }

    public int update() {
        return clienteService.update(data);
    }

    public void searchById() {
        stateView = "edit";
        ///volvemos a realizar busqueda del cliente
        data = clienteService.findByIdClientePk(data.getIdClientePk());
    }

    public String detele() {
        if (data.getEstatus().equals("1")) {
            clienteService.delete(data.getIdClientePk(), usuario.getId());
            JsfUtil.addSuccessMessage("Registro Eliminado Correctamente.");
        } else {
            clienteService.activar(data.getIdClientePk());
            JsfUtil.addSuccessMessage("Registro Activado Correctamente.");
        }

        return "clientes.xhtml";
    }

    public void back() {
        data = new ClienteD();
        stateView = "init";
    }

    private void buscaCliente() {
        model = clienteService.findAll();
    }

    public ArrayList<ClienteD> getModel() {
        return model;
    }

    public void setModel(ArrayList<ClienteD> model) {
        this.model = model;
    }

    public ClienteD getData() {
        return data;
    }

    public void setData(ClienteD data) {
        this.data = data;
    }

    public String getStateView() {
        return stateView;
    }

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

}
