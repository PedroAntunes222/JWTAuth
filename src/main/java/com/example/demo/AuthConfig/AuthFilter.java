package com.example.demo.AuthConfig;

import com.example.demo.TokenConfig.TokenUtil;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.AuthConfig.ErroDTO.sendStatus;

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
                sendStatus(response, "IllegalArgumentException");
                return;
            } catch (ExpiredJwtException e) {
                sendStatus(response, "Expired Token");
                return;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Usuario usuario = usuarioRepository.findByEmail(username);

                if (tokenUtil.validateToken(token, usuario)) {
                    Authentication authentication = tokenUtil.createAuthenticationFromToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}
