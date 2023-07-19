package com.universidad.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.universidad.service.modelos.Virtual;

@FeignClient(name = "virtual-service",url="http://localhost:8002")
@RequestMapping("/virtual")
public interface VirtualFeignClient {

	@PostMapping()
	public Virtual save(@RequestBody Virtual virtual);

	@GetMapping("/universidad/{universidadId}")
	public List<Virtual> getVirtuales(@PathVariable("universidadId") int universidadId);

}
