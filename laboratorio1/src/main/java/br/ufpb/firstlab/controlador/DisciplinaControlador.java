package br.ufpb.firstlab.controlador;

import br.ufpb.firstlab.entidade.Disciplina;
import br.ufpb.firstlab.servico.DisciplinaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
public class DisciplinaControlador {
fd
    @Autowired
    private DisciplinaServico disciplinaService;

    @PostMapping("/v1/api/disciplinas")
    public ResponseEntity<Disciplina> novaDisciplina(@RequestBody Disciplina disciplina){
        return new ResponseEntity<Disciplina>(disciplinaService.addDisciplina(disciplina), HttpStatus.CREATED);
    }

    @GetMapping("/v1/api/disciplinas")
    public ResponseEntity<List<Disciplina>> listarTodas(){
        List<Disciplina> disciplinas = disciplinaService.listarTodas();
        return ResponseEntity.ok().body(disciplinas);
    }

    @GetMapping("/v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> encontrarPorID(@PathVariable int id) {
        try{
            return new ResponseEntity<Disciplina>(disciplinaService.encontrarPorId(id),HttpStatus.OK);
        }
        catch (ArrayIndexOutOfBoundsException e){
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/v1/api/disciplinas/ranking")
    public ResponseEntity<List<Disciplina>> rankingDisciplinas(){
        try{
            return new ResponseEntity<List<Disciplina>>(disciplinaService.ordenarPelaNota(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/v1/api/disciplinas/{id}/nome")
    public ResponseEntity<Disciplina> alterarNome(@PathVariable int id, @RequestBody Disciplina disciplina){
        disciplina.setId(id);
        try {
            return new ResponseEntity<Disciplina>(disciplinaService.alterarNome(disciplina), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/v1/api/disciplinas/{id}/nota")
    public ResponseEntity<Disciplina> alterarNota (@PathVariable int id, @RequestBody Disciplina disciplina){
        disciplina.setId(id);
        try {
            return new ResponseEntity<Disciplina>(disciplinaService.alterarNota(disciplina), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/v1/api/disciplinas/{id}")
    public ResponseEntity<Void> removerDisciplina(@PathVariable int id){
        try {
            disciplinaService.removerDisciplinaPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
