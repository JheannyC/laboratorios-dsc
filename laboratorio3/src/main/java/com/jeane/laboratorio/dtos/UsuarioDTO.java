package com.jeane.laboratorio.dtos;

import com.jeane.laboratorio.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private String email;
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }
}
