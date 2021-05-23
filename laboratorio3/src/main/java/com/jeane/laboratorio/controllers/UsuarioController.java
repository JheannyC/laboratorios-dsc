package com.jeane.laboratorio.controllers;

import com.jeane.laboratorio.exceptions.UsuarioInvalidoException;
import com.jeane.laboratorio.dtos.UsuarioDTO;
import com.jeane.laboratorio.entities.Usuario;
import com.jeane.laboratorio.exceptions.UsuarioJaExisteException;
import com.jeane.laboratorio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criaUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<UsuarioDTO>(usuarioService.criaUsuario(usuario), HttpStatus.CREATED);
        } catch (UsuarioJaExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UsuarioDTO> deletaUsuario(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(usuarioService.deletaUsuario(token), HttpStatus.OK);
        } catch (UsuarioJaExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
