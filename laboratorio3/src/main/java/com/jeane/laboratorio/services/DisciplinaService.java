package com.jeane.laboratorio.services;

import com.jeane.laboratorio.dtos.ComentarioDTO;
import com.jeane.laboratorio.dtos.DisciplinaComentarioDTO;
import com.jeane.laboratorio.dtos.DisciplinaDTO;
import com.jeane.laboratorio.dtos.DisciplinaLikesDTO;
import com.jeane.laboratorio.dtos.DisciplinaNotasDTO;
import com.jeane.laboratorio.dtos.NotaDTO;
import com.jeane.laboratorio.entities.Comentario;
import com.jeane.laboratorio.entities.Disciplina;
import com.jeane.laboratorio.repositories.ComentarioRepository;
import com.jeane.laboratorio.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private DisciplinaRepository disciplinaRepository;
    private ComentarioRepository comentarioRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository, ComentarioRepository comentarioRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public List<DisciplinaDTO> listDisciplinas(){
        List<Disciplina> disciplinaList = disciplinaRepository.findAll();
        List<DisciplinaDTO> listDisciplinas = disciplinaList.stream().map(DisciplinaDTO::new).collect(Collectors.toList());
        return listDisciplinas;
    }

    public Disciplina getDisciplinaById(Long id) throws Exception{
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(id);
        Disciplina disciplinaRec = disciplinaOptional.get();

        if (disciplinaOptional.isEmpty()){
            throw new Exception();
        }
        return disciplinaRec;
    }

    public DisciplinaLikesDTO addLikes(Long id) throws Exception {
        Disciplina disciplinaRec = getDisciplinaById(id);

        int likes = disciplinaRec.getLikes();
        disciplinaRec.setLikes(likes+1);

        DisciplinaLikesDTO disciplinaLikesDTO = new DisciplinaLikesDTO(disciplinaRec);
        disciplinaRepository.save(disciplinaRec);

        return disciplinaLikesDTO;
    }

    public DisciplinaNotasDTO addNota(Long id, NotaDTO notaDTO) throws Exception{
        Disciplina disciplina = getDisciplinaById(id);

        double nota = 0;
        if (disciplina.getNota() == 0) disciplina.setNota(notaDTO.getNota());
        else {
            nota = (disciplina.getNota()+notaDTO.getNota())/2;
            disciplina.setNota(nota);
        }
        disciplinaRepository.save(disciplina);

        return new DisciplinaNotasDTO(disciplina);
    }

    public DisciplinaComentarioDTO addComentarios(Long id, ComentarioDTO comentarioDTO) throws Exception{
        Disciplina disciplina = getDisciplinaById(id);

        Comentario comment = new Comentario(comentarioDTO.getComentario());
        comment.setDisciplina(disciplina);
        comentarioRepository.save(comment);

        disciplina.getComentarios().add(comment);

        DisciplinaLikesDTO disciplinaLikesDTO = new DisciplinaLikesDTO(disciplina);
        disciplinaRepository.save(disciplina);

        return new DisciplinaComentarioDTO(disciplina);
    }

    public List<Disciplina> getRankingNotesDesc() {
        return this.disciplinaRepository.findByOrderByNotaDesc();
    }

    public List<Disciplina> getRankingLikesDesc() {
        return disciplinaRepository.findAll(Sort.by(Sort.Direction.DESC, "likes"));
    }

    /*@PostConstruct
    public void initDisciplinas(){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
        try{
            List<Disciplina>disciplinasAll = mapper.readValue(inputStream, typeReference);
            this.disciplinaRepository.saveAll(disciplinasAll);
            System.out.println("Salvo com sucesso!");
        }
        catch (IOException e){
            System.out.println("Não foi possível salvar as disciplinas!" + e.getMessage());
        }
    }*/
}
