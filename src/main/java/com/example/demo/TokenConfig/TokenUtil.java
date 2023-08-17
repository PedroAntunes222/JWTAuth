package com.example.demo.TokenConfig;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class TokenUtil {
    public static Authentication decodeToken(HttpServletRequest request) {
        if(request.getHeader("Authorization").equals("Bearer *isidro123")){
            return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
        }

        return null;
    }
}
