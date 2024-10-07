/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.util;

import com.fresh.dominio.Menu;
import com.fresh.dominio.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

public class DynamicMenu {

    public static MenuModel getMenuModel(Usuario usuario) {
        MenuModel model = new DefaultMenuModel();

//        for (Menu menu : usuario.getIdRolFk().getAccesMenuCollection()) {

//            addMenu(model, menu);
//        }

        model.generateUniqueIds();

        return model;
    }

//    private static void addMenu(MenuModel model, Menu menu) {
//
////        DefaultSubMenu subMenus = new DefaultSubMenu(menu.getDescripcion());
//        DefaultSubMenu subMenus = DefaultSubMenu.builder().label(menu.getDescripcion()).build();
//
//        DefaultMenuItem items;
//        DefaultSubMenu subCMenus = null;
//
//        for (Menu subMenu : menu.getSubMenu()) {
//
//            if (subMenu.getTipo().equals("1")) {
//
////                items = new DefaultMenuItem(subMenu.getDescripcion());
//                items = DefaultMenuItem.builder().build();
//                items.setValue(subMenu.getDescripcion());
//
//                items.setUrl(subMenu.getUrlSistema());
//                items.setAjax(true);
//
//                //Se inicia con la constuccion del menu en 3 nivel
//                DefaultSubMenu dSubBMenu = DefaultSubMenu.builder().label(subMenu.getDescripcion()).build();
//                DefaultMenuItem itemB;
//
//                for (Menu subBMenu : subMenu.getSubMenu()) {
//
//                    itemB = DefaultMenuItem.builder().value(subBMenu.getDescripcion()).build();
//                    itemB.setUrl(subBMenu.getUrlSistema());
//                    itemB.setAjax(true);
//
//                    if (subBMenu.equals("1")) {
//
//                        //Se inicia con la constuccion del menu en 4 nivel
//                        subCMenus = DefaultSubMenu.builder().label(subBMenu.getDescripcion()).build();
//                        DefaultMenuItem itemC;
//
//                        for (Menu subCMenuDTO : subBMenu.getSubMenu()) {
//
//                            itemC = DefaultMenuItem.builder().value(subCMenuDTO.getDescripcion()).build();
//                            itemC.setUrl(subCMenuDTO.getUrlSistema());
//                            itemC.setAjax(true);
//
//                            subCMenus.getElements().add(itemC);
//
//                        }
//
//                    }
//
//                    if (subCMenus != null) {
//                        dSubBMenu.getElements().add(subCMenus);
//                    } else {
//                        dSubBMenu.getElements().add(itemB);
//                    }
//
//                }
//
//                subMenus.getElements().add(dSubBMenu);
//
//            } else {
//
//                items = DefaultMenuItem.builder().value(subMenu.getDescripcion()).build();
//                items.setUrl(subMenu.getUrlSistema());
//                items.setAjax(true);
//                subMenus.getElements().add(items);
//            }
//
//        }
//
//        model.getElements().add(subMenus);
//
//    }

}
