package com.example.demo.controller;

import com.example.demo.entity.Livro;
import com.example.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
@CrossOrigin("*")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/all")
    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Optional<Livro> findById(@PathVariable Long id){
        return livroRepository.findById(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody Livro livro){
        livroRepository.save(livro);
        return "novo livro adicionado";
    }

    @PutMapping(path = {"/{id}"})
    public String update(@RequestBody Livro livro, @PathVariable Long id) {
        livro.setId(id);
        livroRepository.save(livro);
        return "Livro atualizado";
    }

    @DeleteMapping(path = {"/{id}"})
    public String deleteById(@PathVariable Long id){
        livroRepository.deleteById(id);
        return "Livro deletado";
    }
}
