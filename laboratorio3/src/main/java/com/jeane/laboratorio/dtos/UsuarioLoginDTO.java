package com.jeane.laboratorio.dtos;

import com.jeane.laboratorio.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioLoginDTO {
    private String email;
    private String senha;


    public UsuarioLoginDTO(){}

    public UsuarioLoginDTO(Usuario usuario){
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}
