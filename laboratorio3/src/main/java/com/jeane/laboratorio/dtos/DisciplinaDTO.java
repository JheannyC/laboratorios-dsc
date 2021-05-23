package com.jeane.laboratorio.dtos;

import com.jeane.laboratorio.entities.Disciplina;
import lombok.Data;

@Data
public class DisciplinaDTO {
    private Long id;
    private String nome;

    public DisciplinaDTO(){ }

    public DisciplinaDTO(Disciplina disciplina){
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
    }
}
