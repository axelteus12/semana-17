package com.virtual.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.service.entity.Virtual;
import com.virtual.service.repository.VirtualRepository;

@Service
public class VirtualService {

	@Autowired
	private VirtualRepository virtualRepository;
	
	public List<Virtual> getAll(){
		return virtualRepository.findAll();
	}
	
	public Virtual getVirtualById(int id) {
		return virtualRepository.findById(id).orElse(null);
	}
	
	public Virtual save(Virtual virtual) {
		Virtual nuevoVirtual = virtualRepository.save(virtual);
		return nuevoVirtual;
	}
	
	public List<Virtual> byUniversidadId(int universidadId){
		return virtualRepository.findByUniversidadId(universidadId);
	}

}
