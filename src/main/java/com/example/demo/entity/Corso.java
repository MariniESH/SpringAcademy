package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "corso")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Integer ore;

    @Column(name = "anno_accademico")
    private Integer anno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente")
    private Docente docente;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "corsoalunni",
            joinColumns = {
                    @JoinColumn(name = "id_corso")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id_alunni")
            }
    )
    private List<Alunno> alunni;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getOre() {
        return ore;
    }

    public void setOre(Integer ore) {
        this.ore = ore;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Alunno> getAlunni() {
        return alunni;
    }

    public void setAlunni(List<Alunno> alunni) {
        this.alunni = alunni;
    }
}
