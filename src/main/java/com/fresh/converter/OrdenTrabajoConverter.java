package com.fresh.converter;

import com.fresh.dto.OrdenTrabajoD;
import com.fresh.service.OrdenTrabajoService;
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdenTrabajoConverter implements Converter {

    @Autowired
    OrdenTrabajoService ordenTrabajoService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value != null && !value.equals("null") && value.trim().length() > 0) {

            try {
                System.out.println("busca "+value);
                Object object = ordenTrabajoService.findByIdOrdenPK(Integer.parseInt(value));
                System.out.println("return object");
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

            if (value instanceof OrdenTrabajoD) {
                return String.valueOf(((OrdenTrabajoD) value).getIdOrdenPk());

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
