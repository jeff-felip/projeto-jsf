package com.jefferson.esig.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jefferson.esig.model.Tarefa;
import com.jefferson.esig.repositories.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired
	TarefaRepository tarefaRepository;
	
	 @Transactional
	public Tarefa salvarTarefa(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	public List<Tarefa> TodasTarefas() {
		return tarefaRepository.findAll();
	}

	@Transactional
	public void DeletarTarefa(Tarefa tarefa) {
		tarefaRepository.delete(tarefa);
	}
	
	public List<Tarefa> buscarTarefas(String titulo, String responsavel, String descricao, String situacao) {
		return tarefaRepository.findByTituloStartingWithAndResponsavelStartingWithAndDescricaoStartingWithAndSituacaoStartingWith(titulo, responsavel, descricao, situacao);
	}


}
