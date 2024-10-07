package com.fresh.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author juanc
 */
public class MenuD implements Serializable {

    private Integer idMenu;
    private String descripcion;
    private String tipo;
    private String nivel;
    private String urlSistema;
    private String target;
    private Set<MenuD> subMenu = new HashSet<MenuD>(0);
    private Set<String> allowedUrl = new HashSet<>();

    public MenuD(Integer idMenu, String descripcion, String tipo, String nivel, String urlSistema) {
        this.idMenu = idMenu;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.nivel = nivel;
        this.urlSistema = urlSistema;

    }

    // Getters y Setters
    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getUrlSistema() {
        return urlSistema;
    }

    public void setUrlSistema(String urlSistema) {
        this.urlSistema = urlSistema;
    }

    public Set<MenuD> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Set<MenuD> subMenu) {
        this.subMenu = subMenu;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Set<String> getAllowedUrl() {
        return allowedUrl;
    }

    public void setAllowedUrl(Set<String> allowedUrl) {
        this.allowedUrl = allowedUrl;
    }

    
    
}
