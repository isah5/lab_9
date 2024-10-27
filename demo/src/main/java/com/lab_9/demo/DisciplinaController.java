package com.lab_9.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/disciplinas")

public class DisciplinaController {

    private List<Disciplina> disciplinas = new ArrayList<>();

    @GetMapping
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @PostMapping
    public Disciplina addDisciplina(@RequestBody Disciplina disciplina) {
        disciplinas.add(disciplina);
        return disciplina;
    }

    @GetMapping("/{id}")
    public Disciplina getDisciplinaById(@PathVariable int id) {
        return disciplinas.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Disciplina updateDisciplina(@PathVariable int id, @RequestBody Disciplina disciplina) {
        Disciplina existingDisciplina = disciplinas.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
        if (existingDisciplina != null) {
            existingDisciplina.setNome(disciplina.getNome());
            existingDisciplina.setSigla(disciplina.getSigla());
            existingDisciplina.setCurso(disciplina.getCurso());
            existingDisciplina.setSemestre(disciplina.getSemestre());
        }
        return existingDisciplina;
    }

    @DeleteMapping("/{id}")
    public String deleteDisciplina(@PathVariable int id) {
        disciplinas.removeIf(d -> d.getId() == id);
        return "Disciplina removida";
    }
}

class Disciplina {
    private int id;
    private String nome;
    private String sigla;
    private String curso;
    private String semestre;

    // Getters e Setters para todos os campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
