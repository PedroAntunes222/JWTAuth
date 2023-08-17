package com.example.demo.controller;

import com.example.demo.entity.Genero;
import com.example.demo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genero")
@CrossOrigin("*")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping("/all")
    public List<Genero> getAllGeneros() {
        return generoRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Optional<Genero> findById(@PathVariable Long id){
        return generoRepository.findById(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody Genero genero){
        generoRepository.save(genero);
        return "novo genero adicionado";
    }

    @PutMapping(path = {"/{id}"})
    public String update(@RequestBody Genero genero, @PathVariable Long id) {
        genero.setId(id);
        generoRepository.save(genero);
        return "Genero atualizado";
    }

    @DeleteMapping(path = {"/{id}"})
    public String deleteById(@PathVariable Long id){
        generoRepository.deleteById(id);
        return "Genero deletado";
    }
}
