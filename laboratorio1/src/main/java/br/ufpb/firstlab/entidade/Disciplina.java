package br.ufpb.firstlab.entidade;

import java.util.Objects;

public class Disciplina {

    private int id;
    private String nome;
    private double nota;

    public Disciplina() {
    }

    public Disciplina(int id, String nome, double nota) {
        super();
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return id == that.id && Double.compare(that.nota, nota) == 0 && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nota);
    }
}
