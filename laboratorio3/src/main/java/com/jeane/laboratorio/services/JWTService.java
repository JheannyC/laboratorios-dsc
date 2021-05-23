package com.jeane.laboratorio.services;

import com.jeane.laboratorio.dtos.UsuarioLoginDTO;
import com.jeane.laboratorio.entities.Usuario;
import com.jeane.laboratorio.filters.TokenFilter;
import com.jeane.laboratorio.jwt.LoginResponse;
import com.jeane.laboratorio.repositories.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse autentica(UsuarioLoginDTO usuario) {
        String msgErro = "Email e/ou senha invalido(s). Login nao realizado";
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (optUsuario.isPresent() && usuario.getSenha().equals(optUsuario.get().getSenha()))
            return new LoginResponse(geraToken(usuario));

        return new LoginResponse(msgErro);

    }

    private String geraToken(UsuarioLoginDTO usuario) {
        String token = Jwts.builder().setSubject(usuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();
        return token;
    }

    public static final String TOKEN_KEY = "ja pode sair?";

    public Optional<String> recuperaUsuario(String cabecalhoAutorizacao) {
        if (cabecalhoAutorizacao == null ||
                !cabecalhoAutorizacao.startsWith("Bearer ")) {
            throw new SecurityException();
        }
        String token = cabecalhoAutorizacao.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return Optional.of(subject);
    }

    public String getSujeitoDoToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }


}
