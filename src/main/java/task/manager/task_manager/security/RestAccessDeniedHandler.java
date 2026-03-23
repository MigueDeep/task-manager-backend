package task.manager.task_manager.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import task.manager.task_manager.config.CustomApiResponse;

import java.io.IOException;
import java.util.Map;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        CustomApiResponse<Map<String, Object>> body = new CustomApiResponse<>(
                Map.of(
                        "path", request.getRequestURI(),
                        "details", "No cuentas con permisos suficientes para este recurso"
                ),
                true,
                HttpStatus.FORBIDDEN,
                "Acceso denegado."
        );

        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}

