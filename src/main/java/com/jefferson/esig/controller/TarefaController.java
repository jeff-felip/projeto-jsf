package com.jefferson.esig.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.jefferson.esig.model.Tarefa;
import com.jefferson.esig.repositories.TarefaRepository;


@ManagedBean
@ViewScoped
public class TarefaController {
	
	@Autowired
	private TarefaRepository repository;
	
	private List<Tarefa> tarefas;
	
	private Tarefa tarefa = new Tarefa();
	
	private boolean modeEdicao = false;
	
	@PostConstruct
	public void init() {
		tarefas = repository.findAll();
	}
	
	public void salvar() {
		tarefa.setSituacao("Em Andamento");
		repository.save(tarefa);
		 
		if(!modeEdicao)
			tarefas.add(tarefa);
		
		tarefa = new Tarefa();
		modeEdicao =  false;
		
		FacesMessage menssage = new FacesMessage("Cadastrado com sucesso!");
		FacesContext.getCurrentInstance().addMessage("", menssage);
	}
	
	public void cancelar() {
		tarefa = new Tarefa();
		modeEdicao = false;
	}
	
	public void excluir(Tarefa c) {
		tarefas.remove(c);
		repository.delete(c);
		
		FacesMessage menssage = new FacesMessage("Excluído com sucesso!");
		FacesContext.getCurrentInstance().addMessage("", menssage);
	}
	
	public void editar(Tarefa c) {
		this.tarefa = c;
		modeEdicao = true;
	}
	
	public void concluir(Tarefa c) {
		c.setSituacao("Concluido");
		repository.save(c);
		FacesMessage menssage = new FacesMessage("Tarefa Concluída!");
		FacesContext.getCurrentInstance().addMessage("", menssage);
	}
	
	public void buscar(){
		String responsavel = tarefa.getResponsavel();
		String titulo = tarefa.getTitulo();
		String situacao = tarefa.getSituacao();
		String descricao = tarefa.getDescricao();
		
		
		if(tarefa.getResponsavel().isEmpty()) {
			responsavel = "%";
		}
		if(tarefa.getTitulo().isEmpty()) {
			titulo = "%";
		}
		if(tarefa.getDescricao().isEmpty()) {
			descricao = "%";
		}
		if(tarefa.getSituacao().isEmpty()) {
			situacao = "%";
		}
		
		tarefas = repository.findByTituloStartingWithAndResponsavelStartingWithAndDescricaoStartingWithAndSituacaoStartingWith(titulo, responsavel, descricao, situacao);
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}


	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}


	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public boolean isModeEdicao() {
		return modeEdicao;
	}

	public void setModeEdicao(boolean modeEdicao) {
		this.modeEdicao = modeEdicao;
	}
	

}
