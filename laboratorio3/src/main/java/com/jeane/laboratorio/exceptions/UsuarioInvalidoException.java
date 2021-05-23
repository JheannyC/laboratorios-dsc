package com.jeane.laboratorio.exceptions;

public class UsuarioInvalidoException extends IllegalArgumentException {

    public UsuarioInvalidoException(String s) {
        super(s);
    }

    public UsuarioInvalidoException() {
        super();
    }
}