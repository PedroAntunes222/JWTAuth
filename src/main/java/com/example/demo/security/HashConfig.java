package com.example.demo.security;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashConfig {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HashConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String senha){
        return passwordEncoder.encode(senha);
    }

    public boolean comparePasswords(String senhaLogin, String senhaBD){
        return passwordEncoder.matches(senhaLogin, senhaBD);
    }

}
