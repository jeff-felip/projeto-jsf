package com.jefferson.esig.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jefferson.esig.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	List<Tarefa> findByTituloStartingWithAndResponsavelStartingWithAndDescricaoStartingWithAndSituacaoStartingWith(String titulo, String resposavel, String descricao, String situacao);
	
}
