package com.jeane.laboratorio.controllers;

import com.jeane.laboratorio.dtos.ComentarioDTO;
import com.jeane.laboratorio.dtos.DisciplinaComentarioDTO;
import com.jeane.laboratorio.dtos.DisciplinaDTO;
import com.jeane.laboratorio.dtos.DisciplinaLikesDTO;
import com.jeane.laboratorio.dtos.DisciplinaNotasDTO;
import com.jeane.laboratorio.dtos.NotaDTO;
import com.jeane.laboratorio.entities.Disciplina;
import com.jeane.laboratorio.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/disciplinas")
public class DisciplinaController {

    private DisciplinaService disciplinaService;

    @Autowired
    private DisciplinaController(DisciplinaService disciplinaService){
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinas(){
        return new ResponseEntity<List<DisciplinaDTO>>(disciplinaService.listDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Disciplina> getDisciplinaById(@PathVariable Long id) throws Exception{
        try {
            return new ResponseEntity<>(disciplinaService.getDisciplinaById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/likes/{id}")
    public ResponseEntity<DisciplinaLikesDTO> addLikes(@PathVariable Long id,  @RequestHeader ("Authorization") String header) throws Exception {
        try {
            return new ResponseEntity<>(disciplinaService.addLikes(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/nota/{id}")
    public ResponseEntity<DisciplinaNotasDTO> addNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO, @RequestHeader ("Authorization") String header) throws Exception {
        try {
            return new ResponseEntity<>(disciplinaService.addNota(id, notaDTO), HttpStatus.OK);
        }catch (Exception e){
        return new ResponseEntity<DisciplinaNotasDTO>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/comentarios/{id}")
    public ResponseEntity<DisciplinaComentarioDTO> addComentario(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO, @RequestHeader ("Authorization") String header) throws Exception {
        try {

            return new ResponseEntity<>(disciplinaService.addComentarios(id, comentarioDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/ranking/likes")
    public ResponseEntity<List<Disciplina>> getRankingLikesDesc() {
        return new ResponseEntity<>(disciplinaService.getRankingLikesDesc(), HttpStatus.OK);
    }
    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Disciplina>> getRankingNotesDesc() {
        return new ResponseEntity<>(disciplinaService.getRankingNotesDesc(), HttpStatus.OK);
    }
}
