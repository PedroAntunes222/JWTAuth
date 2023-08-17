package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.security.HashConfig;
import com.example.demo.repository.UsuarioRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    HashConfig hashConfig;

    @GetMapping("login")
    public String Login() {
        return "Pagina de login";
    }

    @PostMapping("login")
    public String LoginForm(@RequestBody Usuario usuario) {
       // var newSenha = hashConfig.encodePassword(usuario.getSenha());
        var logon = usuarioRepository.findByEmail(usuario.getEmail());
        if(logon != null){
            if(hashConfig.comparePasswords(usuario.getSenha(), logon.getSenha())){
                return "Logado";
            }
        };
        return "Erro no login";
    }

    @GetMapping("register")
    public String Register() {
        return "Pagina de registro";
    }

    @PostMapping("register")
    public String sendRegister(@RequestBody Usuario usuario) {
        var newSenha = hashConfig.encodePassword(usuario.getSenha());
        var newUsuario = new Usuario(usuario.getNome(), usuario.getEmail(), newSenha);
        usuarioRepository.save(newUsuario);
        return "Usuario adicionado";
    }
}
