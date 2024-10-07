package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.MateriaPrimaProductoD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.OrdenTrabajoLote;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaProductoService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.OrdenTrabajoLoteService;
import com.fresh.service.OrdenTrabajoProductoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.util.JsfUtil;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class CuartoSucioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(CuartoSucioController.class);

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private OrdenTrabajoLoteService ordenTrabajoLoteService;

    @Autowired
    private EntradaMateriaPrimaProductoService entradaMateriaPrimaProductoService;

    private MateriaPrimaProductoD dataT;
    private OrdenTrabajoD ordenTrabajo;
    private ClienteD cliente;
    private UsuarioD usuario;
    private MateriaPrimaD entrada;

    private String stateView;

    private List<MateriaPrimaProductoD> lstMateriaPrimaProducto;

    @PostConstruct
    public void init() {
        stateView = "init";

        usuario = infoUsuarioController.getUsuario();

        lstMateriaPrimaProducto = new ArrayList<>();

    }

    private String validaInsert() {
        String mensaje = "";

        if (ordenTrabajo == null || ordenTrabajo.getIdOrdenPk() == null) {
            mensaje = "Selecciona una Orden de Trabajo";
        }

        if (entrada == null || entrada.getIdEntradaMateriaPrimaPK() == null) {
            mensaje = "Selecciona una Entrada";
        }
        return mensaje;
    }

    private void reset() {
        ordenTrabajo = new OrdenTrabajoD();
        cliente = new ClienteD();
        entrada = new MateriaPrimaD();
        lstMateriaPrimaProducto = new ArrayList<>();
    }

    public void buscaDatos() {
        buscaCliente();
        buscaProductoMateriaPrima();

    }

    private void buscaProductoMateriaPrima() {
        lstMateriaPrimaProducto = entradaMateriaPrimaProductoService.getByIdEntradaPk(entrada.getIdEntradaMateriaPrimaPK());
    }

    public void pelar() {
        String mensaje = validaInsert();
        if (mensaje.isEmpty()) {
            dataT.setIdOrdenFk(ordenTrabajo.getIdOrdenPk());
            dataT.setFechaPelado(new Date());
            entradaMateriaPrimaProductoService.pelar(dataT);
            buscaProductoMateriaPrima();
            JsfUtil.addSuccessMessage("Registro Modificado Correctamente.");
        } else {
            JsfUtil.addErrorMessage(mensaje);
        }

    }

    public void remover() {

        JsfUtil.addSuccessMessage("Registro Removido Correctamente.");

    }

    public void buscaCliente() {
        if (entrada.getIdClienteFK() != null) {
            cliente = clienteService.findByIdClientePk(entrada.getIdClienteFK());
        }
    }

    public List<MateriaPrimaD> autoCompleteLote(String lote) {
        return entradaMateriaPrimaService.findByLote(lote);
    }

    public List<OrdenTrabajoD> autoCompleteOrdenTrabajo(String ordenTrabajo) {
        return ordenTrabajoService.findByOrdenTrabajo(ordenTrabajo);
    }

    public ArrayList<ClienteD> autoCompleteCliente(String nombreCliente) {
        return clienteService.findByIdNombre(nombreCliente);
    }

    public UsuarioD getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioD usuario) {
        this.usuario = usuario;
    }

    public ClienteD getCliente() {
        return cliente;
    }

    public void setCliente(ClienteD cliente) {
        this.cliente = cliente;
    }

    public MateriaPrimaD getEntrada() {
        return entrada;
    }

    public void setEntrada(MateriaPrimaD entrada) {
        this.entrada = entrada;
    }

    public OrdenTrabajoD getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoD ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public MateriaPrimaProductoD getDataT() {
        return dataT;
    }

    public void setDataT(MateriaPrimaProductoD dataT) {
        this.dataT = dataT;
    }

    
    public String getStateView() {
        return stateView;
    }
    

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

    public List<MateriaPrimaProductoD> getLstMateriaPrimaProducto() {
        return lstMateriaPrimaProducto;
    }

    public void setLstMateriaPrimaProducto(List<MateriaPrimaProductoD> lstMateriaPrimaProducto) {
        this.lstMateriaPrimaProducto = lstMateriaPrimaProducto;
    }

}
