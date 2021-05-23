package com.jeane.laboratorio.entities;

import com.jeane.laboratorio.dtos.DisciplinaLikesDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {

    private final static long serialVersionUID = -2677445042881047798L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int likes;
    private double nota;

    @OneToMany(mappedBy = "disciplina")
    private List<Comentario> comentarios = new ArrayList<>();

    public Disciplina() {
        super();
    }

    public Disciplina(Long id, String nome, int likes, double nota, List<Comentario> comentarios) {
        this.id = id;
        this.nome = nome;
        this.likes = likes;
        this.nota = nota;
        this.comentarios = comentarios;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public int getLikes() {
        return likes;
    }

    public int setLikes(int likes) {
        this.likes = likes;
        return likes;
    }

    public double getNota() {
       return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
