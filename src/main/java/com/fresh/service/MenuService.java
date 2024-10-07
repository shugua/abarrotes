package com.fresh.service;

import com.fresh.dominio.Menu;
import com.fresh.dominio.Usuario;
import com.fresh.dto.MenuD;
import com.fresh.dto.UsuarioD;
import com.fresh.repository.MenuRepository;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class MenuService implements Serializable {

    @Autowired
    MenuRepository menuRepository;

    private MenuD convertirEntidadADTO(Menu m) {
        MenuD dto = new MenuD(m.getIdMenu(), m.getDescripcion(), m.getTipo(), m.getNivel(), m.getUrlSistema());

        return dto;
    }
//

    public ArrayList<MenuD> findByIdRolFk(Integer idRol) {
        List<Menu> lstMenu = new ArrayList<>();
        lstMenu = menuRepository.findByIdRolFk(idRol);

        ArrayList<MenuD> lstMenuD = new ArrayList<>();
        for (Menu m : lstMenu) {
            lstMenuD.add(convertirEntidadADTO(m));
        }

        return lstMenuD;
    }

    public Set<String> getAllowedUrl(Integer idRol) {
        List<Menu> lstMenu = new ArrayList<>();
        lstMenu = menuRepository.findByIdRolFk(idRol);

        Set<String> allowedUrl = new HashSet<>();
        for (Menu m : lstMenu) {
            allowedUrl.add(m.getUrlSistema());
        }

        return allowedUrl;
    }

    public ArrayList<MenuD> ConstuctMenu(Integer idRol) {
        MenuD menuDTO = null;
        MenuD subMenuDTO = null;
        MenuD subBMenuDTO = null;
        MenuD subCMenuDTO = null;
        LinkedHashSet<MenuD> subMenusDTO = null;
        LinkedHashSet<MenuD> subBMenusDTO = null;
        LinkedHashSet<MenuD> subCMenusDTO = null;

        int contNivel = 0;
        int nivel = 0;
        String menuNivel = "";
        String subMenuNivel = "";
        String subMenuNivelTres = "";

        ArrayList<MenuD> menu = new ArrayList<>();
        ////traemos el menuu y lo setaemos en su dto

        List<MenuD> lstMenuD = new ArrayList<>();
        lstMenuD = findByIdRolFk(idRol);
        ////traemos el menuu y lo setaemos en su dto

//        orderListMenu(lstMenuD);
        for (MenuD mD : lstMenuD) {
            nivel = StringUtils.countOccurrencesOf(mD.getNivel(), ".");
            switch (nivel) {
                case 0:
                    contNivel++;
                    menuDTO = new MenuD(mD.getIdMenu(), mD.getDescripcion(), mD.getTipo(), mD.getNivel(), mD.getUrlSistema());
                    menuDTO.setTarget("menu" + contNivel);
                    menuNivel = mD.getNivel();
                    subMenusDTO = new LinkedHashSet<>();
                    menu.add(menuDTO);
                    break;
                case 1:
                    contNivel++;
                    subMenuDTO = new MenuD(mD.getIdMenu(), mD.getDescripcion(), mD.getTipo(), mD.getNivel(), mD.getUrlSistema());
                    subMenuDTO.setTarget("menu" + contNivel);
                    subMenuNivel = mD.getNivel();
                    if (subMenusDTO != null && subMenuDTO.getNivel().contains(menuNivel)) {
                        subMenusDTO.add(subMenuDTO);
                    }
                    if (subMenuDTO.getTipo().equals("1")) {
                        subBMenusDTO = new LinkedHashSet<>();
                    }
                    break;
                case 2:
                    contNivel++;
                    subBMenuDTO = new MenuD(mD.getIdMenu(), mD.getDescripcion(), mD.getTipo(), mD.getNivel(), mD.getUrlSistema());
                    subBMenuDTO.setTarget("menu" + contNivel);
                    subMenuNivelTres = mD.getNivel();
                    if (subBMenuDTO.getTipo().equals("1")) {
                        subCMenusDTO = new LinkedHashSet<>();
                    }
                    if (subBMenuDTO.getNivel().contains(subMenuNivel)) {
                        subBMenusDTO.add(subBMenuDTO);
                    }
                    break;
                case 3:
                    subCMenuDTO = new MenuD(mD.getIdMenu(), mD.getDescripcion(), mD.getTipo(), mD.getNivel(), mD.getUrlSistema());
                    subCMenusDTO.add(subCMenuDTO);
                    break;
                default:
                    break;
            }

            if (menuDTO != null) {

                if (subMenuDTO != null && subMenuDTO.getNivel().contains(menuNivel)) {
                    menuDTO.setSubMenu(subMenusDTO);

                    if (subBMenuDTO != null && subBMenuDTO.getNivel().contains(subMenuNivel)) {
                        subMenuDTO.setSubMenu(subBMenusDTO);

                        if (subCMenuDTO != null && subCMenuDTO.getNivel().contains(subMenuNivelTres)) {
                            subBMenuDTO.setSubMenu(subCMenusDTO);
                        }
                    }
                }
            
            }
        }

        return menu;
    }

    private void orderListMenu(List<MenuD> lstMenu) {
        Collections.sort(lstMenu, new Comparator<MenuD>() {
            public int compare(MenuD menuOne, MenuD menuTwo) {
                BigDecimal one = BigDecimal.ZERO;
                BigDecimal two = BigDecimal.ZERO;
                if (menuOne.getNivel().contains(".")) {
                    String[] temp = menuOne.getNivel().split("\\.");
                    one = new BigDecimal(temp[0]);
                } else {
                    one = new BigDecimal(menuOne.getNivel());
                }
                if (menuTwo.getNivel().contains(".")) {
                    String[] temp = menuTwo.getNivel().split("\\.");
                    two = new BigDecimal(temp[0]);
                } else {
                    two = new BigDecimal(menuTwo.getNivel());
                }
                return one.compareTo(two);
            }
        });
    }

}
