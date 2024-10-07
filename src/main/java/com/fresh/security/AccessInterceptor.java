package com.fresh.security;

import com.fresh.controller.InfoUsuarioController;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author juanc
 */
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private InfoUsuarioController infoUsuarioController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Obtener la URL solicitada
        String requestedUrl = request.getRequestURI();
        if (isStaticResource(requestedUrl)) {
            // Si es un recurso estático, simplemente permite el acceso sin verificar
            return true;
        }
        // Obtener la lista de pantallas asignadas al usuario (desde la base de datos)
        // Validar si la URL está en la lista de pantallas asignadas
        Set<String> assignedScreens = infoUsuarioController.getUsuario().getAllowedUrl(); // Implementa esta función
        if (!assignedScreens.contains(requestedUrl)) {
            // Redirigir o mostrar un mensaje de acceso denegado
            response.sendRedirect("/Fresh/index.xhtml");
            return false;
        }

        return true;
    }

    private boolean isStaticResource(String url) {
        // Verificar si la URL es para un recurso estático (imagen, CSS, JavaScript, etc.)
        return url.contains("/error") || url.contains("javax.faces.resource") ||url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".gif") || url.endsWith(".ico") || url.endsWith(".pdf");
    }
}
