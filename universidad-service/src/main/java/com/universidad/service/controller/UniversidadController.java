package com.universidad.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.service.entity.Universidad;
import com.universidad.service.modelos.Presencial;
import com.universidad.service.modelos.Virtual;
import com.universidad.service.servicio.UniversidadService;

@RestController
@RequestMapping("/universidad")
public class UniversidadController {

	
	@Autowired
	private UniversidadService universidadService;
	
	@GetMapping
	public ResponseEntity<List<Universidad>> listarUniversidad(){
		List<Universidad> universidades = universidadService.getAll();
		if(universidades.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(universidades);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Universidad> obtenerUniversidad(@PathVariable("id") int id){
		Universidad universidad = universidadService.getUniversidadById(id);
		if(universidad == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(universidad);
	}
	
	@PostMapping
	public ResponseEntity<Universidad> guardarUniversidad(@RequestBody Universidad universidad){
		Universidad nuevaUniversidad = universidadService.save(universidad);
		return ResponseEntity.ok(nuevaUniversidad);
	}
	
	
	@GetMapping("/virtuales/{universidadId}")
	public ResponseEntity<List<Virtual>> listarVirtuales(@PathVariable("universidadId")int id){
		Universidad universidad = universidadService.getUniversidadById(id);
		if(universidad == null) {
			return ResponseEntity.notFound().build();
		}
		List<Virtual> virtuales = universidadService.getVirtuales(id);
		return ResponseEntity.ok(virtuales);
	}
	
	@GetMapping("/presenciales/{universidadId}")
	public ResponseEntity<List<Presencial>> listarPresenciales(@PathVariable("universidadId")int id){
		Universidad universidad = universidadService.getUniversidadById(id);
		if(universidad == null) {
			return ResponseEntity.notFound().build();
		}
		List<Presencial> presenciales = universidadService.getPresenciales(id);
		return ResponseEntity.ok(presenciales);
	}
	
	@PostMapping("/virtual/{universidadId}")
	public ResponseEntity<Virtual> guardarVirtual(@PathVariable("universidadId") int universidadId, @RequestBody Virtual virtual){
		Virtual nuevoVirtual = universidadService.saveVirtual(universidadId, virtual);
		return ResponseEntity.ok(nuevoVirtual);
	}

	@PostMapping("/presencial/{universidadId}")
	public ResponseEntity<Presencial> guardarPresencial(@PathVariable("universidadId") int universidadId, @RequestBody Presencial presencial){
		Presencial nuevoPresencial = universidadService.savePresencial(universidadId, presencial);
		return ResponseEntity.ok(nuevoPresencial);
	}
	
	@GetMapping("/todos/{universidadId}")
	public ResponseEntity<Map<String, Object>> listarTodosLosCursos(@PathVariable("universidadId") int universidadId){
		Map<String, Object> resultado = universidadService.getVirtualAndPresencial(universidadId);
		return ResponseEntity.ok(resultado);
	}


	
}






