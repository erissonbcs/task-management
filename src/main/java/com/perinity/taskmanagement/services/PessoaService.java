package com.perinity.taskmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.perinity.taskmanagement.dto.PessoaDTO;
import com.perinity.taskmanagement.entities.Departamento;
import com.perinity.taskmanagement.entities.Pessoa;
import com.perinity.taskmanagement.entities.Tarefa;
import com.perinity.taskmanagement.reports.RelatorioHorasGasta;
import com.perinity.taskmanagement.reports.RelatorioMediaHorasGasta;
import com.perinity.taskmanagement.repositories.DepartamentoRepository;
import com.perinity.taskmanagement.repositories.PessoaRepository;
import com.perinity.taskmanagement.services.exceptions.EntityNotFoundException;
import com.perinity.taskmanagement.utils.GenericReturn;

@Service
public class PessoaService {
	
	PessoaRepository pessoaRepository;
	DepartamentoRepository departamentoRepository;

	public PessoaService(PessoaRepository pessoaRepository, DepartamentoRepository departamentoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.departamentoRepository = departamentoRepository;
	}
	
    public Pessoa salvar(PessoaDTO pessoaDTO) {
    	Pessoa pessoa = new Pessoa(pessoaDTO.getNome(), new Departamento(pessoaDTO.getIdDepartamento()));
    	buscarDepartamentoPorId(pessoaDTO.getIdDepartamento());
    	
    	return pessoaRepository.save(pessoa);
    }
	
    private Departamento buscarDepartamentoPorId(Long id) {
    	Optional<Departamento> obj = departamentoRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Departamento informado não foi encontrado."));
    }
    
    public Pessoa atualizar(Long id, PessoaDTO pessoaDTO) {
    	Pessoa pessoa = buscarPessoaPorId(id);
    	Departamento depatamento = buscarDepartamentoPorId(pessoaDTO.getIdDepartamento());
    	
    	pessoa.setDepartamento(depatamento);
    	pessoa.setNome(pessoaDTO.getNome());
  
    	return pessoaRepository.save(pessoa);
    }
    
    private Pessoa buscarPessoaPorId(Long id) {
    	Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Pessoa informada não foi encontrada."));
    }
    
    public GenericReturn deletar(Long id) {
    	Pessoa pessoa = buscarPessoaPorId(id);
    	pessoaRepository.delete(pessoa);
    	
    	GenericReturn genericReturn = new GenericReturn(HttpStatus.OK.value(), "Pessoa deletada com sucesso!", pessoa);
    	
    	return genericReturn;	
    }
    
    
    public List<RelatorioHorasGasta> listar(){
    	List<Pessoa> pessoas = pessoaRepository.findAll();
    	List<RelatorioHorasGasta> listaPessoas = new ArrayList<RelatorioHorasGasta>();
    	RelatorioHorasGasta relatorio;
    	
    	for(Pessoa pessoa: pessoas) {
    		int totalHoras = calculaTotalHorasGastas(pessoa.getTarefas());
    		relatorio = new RelatorioHorasGasta(pessoa.getNome(), pessoa.getDepartamento(), totalHoras);
    		listaPessoas.add(relatorio);
    	}
    	  
    	return listaPessoas;
    }
    
    
    private int calculaTotalHorasGastas(List<Tarefa> tarefas) {
    	int totalHoras = 0;
    		
    	if(tarefas.size() > 0) {
    		for(Tarefa tarefa: tarefas) {
    			totalHoras += (tarefa.getDuracao() * 8);
    		}
    	}
    	
    	return totalHoras;
    }
    
    
    public List<RelatorioMediaHorasGasta> buscar(RelatorioMediaHorasGasta relatorioMediaHorasGasta){
    	List<Pessoa> pessoas = pessoaRepository.findByNomeAndDataInicioAfterAndDataFimBefore(
    			relatorioMediaHorasGasta.getNome(), relatorioMediaHorasGasta.getDataInicio(), 
    			relatorioMediaHorasGasta.getDataFim());
    	
    	List<RelatorioMediaHorasGasta> listaPessoas = new ArrayList<RelatorioMediaHorasGasta>();
    	RelatorioMediaHorasGasta relatorio;
    	
    	for(Pessoa pessoa: pessoas) {
    		int medialHoras = (calculaTotalHorasGastas(pessoa.getTarefas())/pessoas.size());
    		relatorio = new RelatorioMediaHorasGasta();
    		relatorio.setNome(relatorioMediaHorasGasta.getNome());
    		relatorio.setDataInicio(relatorioMediaHorasGasta.getDataInicio());
    		relatorio.setDataFim(relatorioMediaHorasGasta.getDataFim());
    		relatorio.setMediaHoras(medialHoras);
    		relatorio.setPessoa(pessoa);
    		listaPessoas.add(relatorio);
    	}
    	  
    	return listaPessoas;
    }
}
