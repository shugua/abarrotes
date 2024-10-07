package com.fresh.controller;

import com.fresh.dto.ProveedorD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ProveedorService;
import com.fresh.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProveedorController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(ProveedorController.class);

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;

    private ArrayList<ProveedorD> model;
    private ProveedorD data;
    private UsuarioD usuario;

    private String tipo;
    private String stateView;

    @PostConstruct
    public void init() {

        tipo ="1";
        stateView = "init";
        buscaProveedor();
        usuario = infoUsuarioController.getUsuario();

    }

    public void viewNew() {
        stateView = "new";
        data = new ProveedorD();
        data.setTipoProveedor(1);
        data.setDivisa("MXN");
        data.setFormaPago(1);
        data.setTerminoPago("1");
    }

    public String save() {
        if (data.getIdProveedorPk() == null) {
            insert();
            JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
            return "proveedores.xhtml";
        } else {
            update();
            JsfUtil.addSuccessMessage("Registro Modificado Correctamente.");
            return "proveedores.xhtml";
        }

    }

    public int insert() {
        data.setEstatus("1");

        data.setClave(generaClave(data.getTipoProveedor()));
        data.setIdUsuarioAlta(usuario.getId());
        return proveedorService.insert(data);
    }

    public int update() {
        return proveedorService.update(data);
    }

    public void searchById() {
        stateView = "edit";
        ///volvemos a realizar busqueda del proveedor
        data = proveedorService.findByIdProveedorPk(data.getIdProveedorPk());
    }

    public String detele() {
        if (data.getEstatus().endsWith("1")) {
            proveedorService.delete(data.getIdProveedorPk(), usuario.getId());
            JsfUtil.addSuccessMessage("Registro Eliminado Correctamente.");
        } else {
            proveedorService.activar(data.getIdProveedorPk());
            JsfUtil.addSuccessMessage("Registro Activado Correctamente.");
        }

        return "proveedores.xhtml";

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    private String generaClave(Integer tipoProveedor) {
        String clave;
        int nextVal = proveedorService.getNextValTipoProveedor(tipoProveedor);
        
        if(tipoProveedor == 1){////si es servicio
            clave ="PVSE";
        }else {///si es producto
          clave ="PVPR";  
        }
        
        if (nextVal < 10) {////si es servicio
            clave = clave + "00";
        } else if (nextVal < 100) {///si es producto
            clave = clave + "0";
        }
        clave = clave + nextVal;
            
        return clave;
    }

    public void back() {
        data = new ProveedorD();
        stateView = "init";
    }

    public void buscaProveedor() {
        model = proveedorService.findByTipo(tipo);
    }

    public UsuarioD getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioD usuario) {
        this.usuario = usuario;
    }

    public ArrayList<ProveedorD> getModel() {
        return model;
    }

    public void setModel(ArrayList<ProveedorD> model) {
        this.model = model;
    }

    public ProveedorD getData() {
        return data;
    }

    public void setData(ProveedorD data) {
        this.data = data;
    }

    public String getStateView() {
        return stateView;
    }

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

}
