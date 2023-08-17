package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "capa", nullable = false)
    private String capa;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "subTitulo", nullable = false)
    private String subTitulo;

    @Column(name = "generoPrincipal", nullable = false)
    private String generoPrincipal;

    @Column(name = "generoSecundario", nullable = false)
    private String generoSecundario;

    @Column(name = "sinopse", nullable = false, length = Integer.MAX_VALUE)
    private String sinopse;

    @Column(name = "paginasLidas", nullable = false)
    private int paginasLidas;

    @Column(name = "paginasTotais", nullable = false)
    private int paginasTotais;

    @Column(name = "rating", nullable = false)
    private float rating;

    @Column(name = "completo", nullable = false)
    private Boolean completo;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
