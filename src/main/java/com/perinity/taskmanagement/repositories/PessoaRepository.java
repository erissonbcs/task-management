package com.perinity.taskmanagement.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.perinity.taskmanagement.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	
	@Query(value ="select p.* from pessoas p, tarefas t where p.id = t.id_pessoa and  p.nome = ? and  to_char(t.prazo, 'yyyy-mm-dd') BETWEEN ? and ?", nativeQuery = true)
	List<Pessoa> findByNomeAndDataInicioAfterAndDataFimBefore(String nome, Date DataInicio, Date DataFim);
	

}
