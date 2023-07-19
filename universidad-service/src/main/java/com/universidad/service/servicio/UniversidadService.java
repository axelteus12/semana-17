package com.universidad.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.universidad.service.entity.Universidad;
import com.universidad.service.feignclients.PresencialFeignClient;
import com.universidad.service.feignclients.VirtualFeignClient;
import com.universidad.service.modelos.Presencial;
import com.universidad.service.modelos.Virtual;
import com.universidad.service.repository.UniversidadRepository;

@Service
public class UniversidadService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UniversidadRepository universidadRepository;
	
	@Autowired
	private VirtualFeignClient virtualFeignClient;
	
	@Autowired
	private PresencialFeignClient presencialFeignClient;
	
	public List<Universidad> getAll(){
		return universidadRepository.findAll();
	}
	
	public Universidad getUniversidadById(int id) {
		return universidadRepository.findById(id).orElse(null);
	}
	
	public Universidad save(Universidad universidad) {
		Universidad nuevaUniversidad = universidadRepository.save(universidad);
		return nuevaUniversidad;
	}
	
	
	public List<Virtual> getVirtuales(int universidadId) {
		List<Virtual> virtuales = restTemplate.getForObject("http://localhost:8002/virtual/universidad/" + universidadId, List.class);
		return virtuales;
	}
	
	public List<Presencial> getPresenciales(int universidadId) {
		List<Presencial> presenciales = restTemplate.getForObject("http://localhost:8003/presencial/universidad/" + universidadId, List.class);
		return presenciales;
	}

	
	public Virtual saveVirtual(int universidadId, Virtual virtual) {
		virtual.setUniversidadId(universidadId);
		Virtual nuevoVirtual = virtualFeignClient.save(virtual);
		return nuevoVirtual;
	}
	
	public Presencial savePresencial(int universidadId, Presencial presencial) {
		presencial.setUniversidadId(universidadId);
		Presencial nuevoPresencial = presencialFeignClient.save(presencial);
		return nuevoPresencial;
	}
	
	
	public Map<String, Object> getVirtualAndPresencial(int universidadId){
		Map<String, Object> resultado = new HashMap<>();
		Universidad universidad = universidadRepository.findById(universidadId).orElse(null);
		
		if(universidad == null) {
			resultado.put("Mensaje", "La universidad no existe");
			return resultado;
		}
		
		resultado.put("Universidad", universidad);
		List<Virtual> virtuales = virtualFeignClient.getVirtuales(universidadId);
		if(virtuales.isEmpty()) {
			resultado.put("Virtuales", "La Universidad no tiene virtuales");
		} else {
			resultado.put("Virtuales", virtuales);
		}
		
		List<Presencial> presenciales = presencialFeignClient.getPresenciales(universidadId);
		if(presenciales.isEmpty()) {
			resultado.put("Presenciales", "La Universidad no tiene presenciales");
		} else {
			resultado.put("Presenciales", presenciales);
		}
		
		return resultado;
	}


}






