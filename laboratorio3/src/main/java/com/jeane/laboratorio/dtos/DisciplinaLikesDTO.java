package com.jeane.laboratorio.dtos;

import com.jeane.laboratorio.entities.Disciplina;
import lombok.Data;

@Data
public class DisciplinaLikesDTO {
    private Long id;
    private String nome;
    private int likes;

    public DisciplinaLikesDTO(Disciplina disciplina){
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.likes = disciplina.getLikes();
    }

}
