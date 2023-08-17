package com.example.demo.AuthConfig;

import com.example.demo.TokenConfig.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization") != null){
            Authentication auth = TokenUtil.decodeToken(request);

            if(TokenUtil.decodeToken(request) != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                ErroDTO erro = new ErroDTO(401, "Não autorizado");
                response.setStatus(erro.getStatus());
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
