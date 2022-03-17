package com.perinity.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perinity.taskmanagement.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
}
