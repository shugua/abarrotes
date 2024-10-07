package com.fresh.converter;

import com.fresh.dto.ClienteD;
import com.fresh.service.ClienteService;
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter implements Converter {

    @Autowired
    ClienteService clienteService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value != null && !value.equals("null") && value.trim().length() > 0) {

            try {

                Object object = clienteService.findByIdClientePk(Integer.parseInt(value));

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

            if (value instanceof ClienteD) {
                return String.valueOf(((ClienteD) value).getIdClientePk());

            } else {
                return String.valueOf(value);

            }
        } else {

            return null;

        }
    }

    private BigDecimal validarNumero(String value) {
        try {

            BigDecimal monto = new BigDecimal(value);

            return monto;

        } catch (NumberFormatException e) {
            return new BigDecimal("0");
        }
    }

}
