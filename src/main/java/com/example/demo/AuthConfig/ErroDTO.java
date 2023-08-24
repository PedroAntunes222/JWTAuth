package com.example.demo.AuthConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErroDTO {
    private int status;
    private String message;

    public static void sendStatus(HttpServletResponse response, String errorDescription){
        ErroDTO erro = new ErroDTO(401, errorDescription);
        response.setStatus(erro.getStatus());
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.getWriter().print(mapper.writeValueAsString(erro));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
