package com.fresh.converter;

import com.fresh.dto.ProveedorD;
import com.fresh.service.ProveedorService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProveedorConverter implements Converter {

    @Autowired
    ProveedorService proveedorService;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.equals("null") && value.trim().length() > 0) {

            try {
                if ((value.matches("[+-]?\\d*(\\.\\d+)?") && value.equals("") == false)) {
                    Object object = proveedorService.findByIdProveedorPk(Integer.parseInt(value));
                    System.out.println("encontrado ");
                    return object;
                } else {
                    return null;
                }

            } catch (Exception e) {

                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error >" + e.getStackTrace(), " - " + e.getStackTrace()));
            }
        } else {

            return null;

        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {

            if (value instanceof ProveedorD) {

                return String.valueOf(((ProveedorD) value).getIdProveedorPk());

            } else {

                return String.valueOf(value);

            }
        } else {

            return null;

        }
    }
    

}
