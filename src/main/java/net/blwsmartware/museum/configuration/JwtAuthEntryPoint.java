package net.blwsmartware.museum.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.JwtAuthException;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        log.error("Access has been killed by JwtAuthEntryPoint: {}",authException.getMessage());
        ErrorResponse err = ErrorResponse.ACCESS_DENIED;

        if (authException instanceof JwtAuthException) {
            log.error("AuthenticationException catch JwtAuthException : {} ",authException.getCause().getMessage());

            try {
                err = ErrorResponse.valueOf(authException.getCause().getMessage());
            }catch (IllegalArgumentException e){
                log.info(e.getMessage());
            }
        }


        response.setStatus(err.getHttpCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        MessageResponse<?> respMessage = MessageResponse.builder()
                .code(err.getCode())
                .message(err.getMessage())
                .success(false)
                .build();
        response.getWriter().write(new ObjectMapper().writeValueAsString(respMessage));
        response.flushBuffer();
    }
}
