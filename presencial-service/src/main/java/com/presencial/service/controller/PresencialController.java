package com.presencial.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presencial.service.entity.Presencial;
import com.presencial.service.servicio.PresencialService;


@RestController
@RequestMapping("/presencial")
public class PresencialController {

	@Autowired
	private PresencialService presencialService;
	
	@GetMapping
	public ResponseEntity<List<Presencial>> listarPresenciales(){
		List<Presencial> presenciales = presencialService.getAll();
		if(presenciales.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(presenciales);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Presencial> obtenerPresencial(@PathVariable("id") int id){
		Presencial presencial = presencialService.getPresencialById(id);
		if(presencial == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(presencial);
	}
	
	@PostMapping
	public ResponseEntity<Presencial> guardarPresencial(@RequestBody Presencial presencial){
		Presencial nuevoPresencial = presencialService.save(presencial);
		return ResponseEntity.ok(nuevoPresencial);
	}
	
	@GetMapping("/universidad/{universidadId}")
	public ResponseEntity<List<Presencial>> listarPresencialPoruniversidadId(@PathVariable("universidadId")int id){
		List<Presencial> presenciales = presencialService.byUniversidadId(id);
		if(presenciales.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(presenciales);
	}
}
