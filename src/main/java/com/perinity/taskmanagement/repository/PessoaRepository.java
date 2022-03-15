package com.perinity.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perinity.taskmanagement.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
