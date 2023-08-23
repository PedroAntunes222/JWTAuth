package com.example.demo.AuthConfig;

import com.example.demo.TokenConfig.TokenUtil;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
            token = requestHeader.substring(7);
            try {
                username = tokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException  e) {
                ErroDTO erro = new ErroDTO(401, "IllegalArgumentException");
                response.setStatus(erro.getStatus());
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            } catch (ExpiredJwtException e) {
                ErroDTO erro = new ErroDTO(401, "ExpiredJwtException");
                response.setStatus(erro.getStatus());
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("ue");
                Usuario usuario = usuarioRepository.findByEmail(username);

                System.out.println("validate");
                System.out.println(tokenUtil.validateToken(token, usuario));

                if (tokenUtil.validateToken(token, usuario)) {
                    Authentication authentication = tokenUtil.createAuthenticationFromToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

/*
        if(request.getHeader("Authorization") != null){
            Authentication auth = TokenUtil.decodeToken(request);

            if(tokenUtil.decodeToken(request) != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                ErroDTO erro = new ErroDTO(401, "Não autorizado 1");
                response.setStatus(erro.getStatus());
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            }
        } else {
            ErroDTO erro = new ErroDTO(401, "Não autorizado 2");
            response.setStatus(erro.getStatus());
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().print(mapper.writeValueAsString(erro));
            response.getWriter().flush();
            return;
        }

 */

        filterChain.doFilter(request, response);
    }

}
