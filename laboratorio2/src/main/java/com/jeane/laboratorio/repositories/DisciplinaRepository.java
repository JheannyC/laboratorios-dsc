package com.jeane.laboratorio.repositories;

import com.jeane.laboratorio.entities.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByOrderByNotaDesc();
    List<Disciplina> findByOrderByLikesDesc();
}
