package com.jeane.laboratorio.dtos;

import com.jeane.laboratorio.entities.Comentario;
import com.jeane.laboratorio.entities.Disciplina;
import lombok.Data;

import java.util.List;

@Data
public class DisciplinaComentarioDTO {
    private Long id;
    private String nome;
    private List<Comentario> comentarios;

    public DisciplinaComentarioDTO(Disciplina disciplina){
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.comentarios = disciplina.getComentarios();
    }
}
