package com.perinity.taskmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.perinity.taskmanagement.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	@Query(value ="select * from tarefas where id_pessoa is null order by prazo limit 3", nativeQuery = true)
	List<Tarefa> findByTarefasAtrasadas();
	
	@Query(value ="select t.* from tarefas t where t.id_departamento = ?", nativeQuery = true)
	List<Tarefa> findByIdDepartamento(Long id);
	
}
