package com.perinity.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perinity.taskmanagement.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
