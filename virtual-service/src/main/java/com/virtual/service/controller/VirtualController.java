package com.virtual.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtual.service.entity.Virtual;
import com.virtual.service.servicio.VirtualService;

@RestController
@RequestMapping("/virtual")
public class VirtualController {

	@Autowired
	private VirtualService virtualService;
	
	@GetMapping
	public ResponseEntity<List<Virtual>> listarVirtuales(){
		List<Virtual> virtuales = virtualService.getAll();
		if(virtuales.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(virtuales);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Virtual> obtenerVirtual(@PathVariable("id") int id){
		Virtual virtual = virtualService.getVirtualById(id);
		if(virtual == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(virtual);
	}
	
	@PostMapping
	public ResponseEntity<Virtual> guardarVirtual(@RequestBody Virtual virtual){
		Virtual nuevoVirtual = virtualService.save(virtual);
		return ResponseEntity.ok(nuevoVirtual);
	}
	
	@GetMapping("/universidad/{universidadId}")
	public ResponseEntity<List<Virtual>> listarVirtualPoruniversidadId(@PathVariable("universidadId")int id){
		List<Virtual> virtuales = virtualService.byUniversidadId(id);
		if(virtuales.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(virtuales);
	}

}
