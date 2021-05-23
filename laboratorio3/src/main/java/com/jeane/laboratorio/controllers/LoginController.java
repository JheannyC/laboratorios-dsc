package com.jeane.laboratorio.controllers;

import com.jeane.laboratorio.dtos.UsuarioLoginDTO;
import com.jeane.laboratorio.services.JWTService;
import com.jeane.laboratorio.jwt.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class LoginController {

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autentica(@RequestBody UsuarioLoginDTO usuario) {
        return new ResponseEntity<LoginResponse>(jwtService.autentica(usuario), HttpStatus.OK);
    }

}
