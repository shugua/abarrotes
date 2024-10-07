package com.fresh.service;

/**
 *
 * @author Juan
 */
import com.fresh.dto.MenuD;
import com.fresh.dto.UsuarioD;
import java.io.Serializable;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Service;

@Service
public class DynamicMenuService implements Serializable{
     private static final long serialVersionUID = 1L;

    public static MenuModel getMenuModel(UsuarioD usuarioDTO) {
        MenuModel model = new DefaultMenuModel();
        for (MenuD menu : usuarioDTO.getMenu()) {
            addMenu(model, menu);
            
        }

        model.generateUniqueIds();

        return model;
    }

    private static void addMenu(MenuModel model, MenuD menu) {

//        DefaultSubMenu subMenus = new DefaultSubMenu(menu.getDescripcion());
        DefaultSubMenu subMenus = DefaultSubMenu.builder().label(menu.getDescripcion()).build();

        DefaultMenuItem items;
        DefaultSubMenu subCMenus = null;

        for (MenuD subMenu : menu.getSubMenu()) {

            if (subMenu.getTipo().equals("1")) {

//                items = new DefaultMenuItem(subMenu.getDescripcion());
                items = DefaultMenuItem.builder().build();
                items.setValue(subMenu.getDescripcion());

                items.setUrl(subMenu.getUrlSistema());
                items.setAjax(true);

                //Se inicia con la constuccion del menu en 3 nivel
                DefaultSubMenu dSubBMenu = DefaultSubMenu.builder().label(subMenu.getDescripcion()).build();
                DefaultMenuItem itemB;

                for (MenuD subBMenu : subMenu.getSubMenu()) {

                    itemB = DefaultMenuItem.builder().value(subBMenu.getDescripcion()).build();
                    itemB.setUrl(subBMenu.getUrlSistema());
                    itemB.setAjax(true);

                    if (subBMenu.getTipo().equals("1")) {

                        //Se inicia con la constuccion del menu en 4 nivel
                        subCMenus = DefaultSubMenu.builder().label(subBMenu.getDescripcion()).build();
                        DefaultMenuItem itemC;

                        for (MenuD subCMenuDTO : subBMenu.getSubMenu()) {

                            itemC = DefaultMenuItem.builder().value(subCMenuDTO.getDescripcion()).build();
                            itemC.setUrl(subCMenuDTO.getUrlSistema());
                            itemC.setAjax(true);

//                            subCMenus.addElement(itemC);
                            subCMenus.getElements().add(itemC);

                        }

                    }

                    if (subCMenus != null) {
                        dSubBMenu.getElements().add(subCMenus);
//                        dSubBMenu.addElement(subCMenus);
                    } else {
                        dSubBMenu.getElements().add(itemB);
//                        dSubBMenu.addElement(itemB);
                    }

                }

                subMenus.getElements().add(dSubBMenu);
//                subMenus.addElement(dSubBMenu);

            } else {

//                items = new DefaultMenuItem(subMenu.getDescripcion());
                items = DefaultMenuItem.builder().value(subMenu.getDescripcion()).build();
                items.setUrl(subMenu.getUrlSistema());
                items.setAjax(true);
                subMenus.getElements().add(items);
//                subMenus.addElement(items);
            }

        }

//        model.addElement(subMenus);
        model.getElements().add(subMenus);

    }

}
