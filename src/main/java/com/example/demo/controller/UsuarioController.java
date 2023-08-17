package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Optional<Usuario> findById(@PathVariable Long id){
        return usuarioRepository.findById(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return "Novo usuario adicionado";
    }

    @PutMapping(path = {"/{id}"})
    public String update(@RequestBody Usuario usuario, @PathVariable Long id) {
        usuario.setId(id);
        usuarioRepository.save(usuario);
        return "Usuario atualizado";
    }

    @DeleteMapping(path = {"/{id}"})
    public String deleteById(@PathVariable Long id){
        usuarioRepository.deleteById(id);
        return "Usuario deletado";
    }
}
