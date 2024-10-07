package com.fresh.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // Configura el mensaje de error que deseas mostrar al cliente
        String errorMessage = "a aaUsuario o contraseña incorrectos 123344";
        // Redirige al usuario a la página de inicio de sesión con el mensaje de error
        response.sendRedirect("/login?error=" + errorMessage);

    }
}
