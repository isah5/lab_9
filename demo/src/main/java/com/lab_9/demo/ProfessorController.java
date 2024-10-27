package com.lab_9.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private List<Professor> professores = new ArrayList<>();

    @GetMapping
    public List<Professor> getProfessores() {
        return professores;
    }

    @PostMapping
    public Professor addProfessor(@RequestBody Professor professor) {
        professores.add(professor);
        return professor;
    }

    @GetMapping("/{id}")
    public Professor getProfessorById(@PathVariable int id) {
        return professores.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable int id, @RequestBody Professor professor) {
        Professor existingProfessor = professores.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (existingProfessor != null) {
            existingProfessor.setNome(professor.getNome());
            existingProfessor.setCurso(professor.getCurso());
        }
        return existingProfessor;
    }

    @DeleteMapping("/{id}")
    public String deleteProfessor(@PathVariable int id) {
        professores.removeIf(p -> p.getId() == id);
        return "Professor removido";
    }
}

class Professor {
    private int id;
    private String nome;
    private String curso;

    // Getters e Setters para os campos id, nome e curso

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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
