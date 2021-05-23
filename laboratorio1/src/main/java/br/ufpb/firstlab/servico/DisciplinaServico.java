package br.ufpb.firstlab.servico;

import br.ufpb.firstlab.entidade.Disciplina;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class DisciplinaServico {

    private final List<Disciplina> disciplinas = new ArrayList<>();

    public Disciplina addDisciplina(Disciplina novaDisciplina){
        novaDisciplina.setId(disciplinas.size()+1);
        disciplinas.add(novaDisciplina);
        return disciplinas.get(disciplinas.size()-1);
    }
    public List<Disciplina> listarTodas(){
        if(disciplinas.isEmpty()) throw new ArrayIndexOutOfBoundsException();
        return disciplinas;
    }
    public Disciplina encontrarPorId(int id){
        if (disciplinas.isEmpty() || id < 0 || id >= disciplinas.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return  disciplinas.get(id-1);
    }

    public Disciplina alterarNome (Disciplina disciplina){
        Disciplina atualizarNome = encontrarPorId(disciplina.getId());
        if (atualizarNome != null) {
            atualizarNome.setNome(disciplina.getNome());
        }
        disciplinas.set(disciplina.getId()-1, atualizarNome);
        return atualizarNome;
    }

    public Disciplina alterarNota(Disciplina disciplina){
        Disciplina atualizarNota = encontrarPorId(disciplina.getId());
        if(atualizarNota != null){
            atualizarNota.setNota(disciplina.getNota());
        }
        disciplinas.set(disciplina.getId()-1, atualizarNota);
        return atualizarNota;
    }

    public void removerDisciplinaPorId(int id){
        for (Disciplina d : disciplinas){
            if (d.getId() == id){
                disciplinas.remove(id);
                break;
            }
        }
    }
    public List<Disciplina> ordenarPelaNota(){
        Collections.sort(disciplinas, (o1, o2) -> {
            if(o1.getNota() > o2.getNota()) return -1;
            if(o1.getNota() < o2.getNota()) return +1;
            else return 0;
        });
        return disciplinas;
    }

}
