package com.perinity.taskmanagement.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.taskmanagement.dto.DepartamentoDTO;
import com.perinity.taskmanagement.services.DepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoResource {
	
	DepartamentoService departamentoService;
	
	public DepartamentoResource(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}
	
	@GetMapping
	public ResponseEntity<List<DepartamentoDTO>> listarDepartamento(){
		List<DepartamentoDTO> relatorio = departamentoService.listarDepartamentos();
		return ResponseEntity.ok().body(relatorio);
	}
}
