package com.fresh.converter;

import com.fresh.dto.ProductoD;
import com.fresh.service.ProductoService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoConverter implements Converter {

    @Autowired
    ProductoService productoService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("null") && value.trim().length() > 0) {

            try {

                Object object = productoService.findByIdProductoPk(Integer.parseInt(value));

                return object;

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

            if (value instanceof ProductoD) {

                return String.valueOf(((ProductoD) value).getIdProductoPk());

            } else {

                return String.valueOf(value);

            }
        } else {

            return null;

        }
    }

}
