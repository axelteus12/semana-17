package com.universidad.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.universidad.service.modelos.Presencial;

@FeignClient(name = "presencial-service",url="http://localhost:8003")
@RequestMapping("/presencial")
public interface PresencialFeignClient {


	@PostMapping()
	public Presencial save(@RequestBody Presencial presencial);

	@GetMapping("/universidad/{universidadId}")
	public List<Presencial> getPresenciales(@PathVariable("universidadId") int universidadId);

}
