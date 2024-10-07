package com.fresh.controller;

import com.fresh.dto.ProductoD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ProductoService;
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
public class ProductoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;

    private ArrayList<ProductoD> model;
    private ProductoD data;
    private UsuarioD usuario;
    private String tipo;
    private String stateView;

    @PostConstruct
    public void init() {

        tipo = "1";
        stateView = "init";
        buscaProducto();
        usuario = infoUsuarioController.getUsuario();

    }

    public void viewNew() {
        stateView = "new";
        data = new ProductoD();
    }

    public String save() {
        if (data.getIdProductoPk() == null) {
            insert();
            JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
            return "productos.xhtml";
        } else {
            update();
            JsfUtil.addSuccessMessage("Registro Modificado Correctamente.");
            return "productos.xhtml";
        }

    }

    public int insert() {
        data.setEstatus("1");
        data.setIdUsuarioAlta(usuario.getId());
        return productoService.insert(data);
    }

    public int update() {
        return productoService.update(data);
    }

    public void searchById() {
        stateView = "edit";
        ///volvemos a realizar busqueda del usuario
        data = productoService.findByIdProductoPk(data.getIdProductoPk());
    }

    public String detele() {
        if (data.getEstatus().endsWith("1")) {
            productoService.delete(data.getIdProductoPk(), usuario.getId());
            JsfUtil.addSuccessMessage("Registro Eliminado Correctamente.");
        } else {
            productoService.activar(data.getIdProductoPk());
            JsfUtil.addSuccessMessage("Registro Activado Correctamente.");
        }

        return "productos.xhtml";

    }

    public void back() {
        data = new ProductoD();
        stateView = "init";
    }

    public void buscaProducto() {
        model = productoService.findByTipo(tipo);
    }

    public UsuarioD getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioD usuario) {
        this.usuario = usuario;
    }

    public ArrayList<ProductoD> getModel() {
        return model;
    }

    public void setModel(ArrayList<ProductoD> model) {
        this.model = model;
    }

    public ProductoD getData() {
        return data;
    }

    public void setData(ProductoD data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStateView() {
        return stateView;
    }

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

}
