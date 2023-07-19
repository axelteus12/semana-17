package com.virtual.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtual.service.entity.Virtual;

@Repository
public interface VirtualRepository extends JpaRepository<Virtual, Integer>{
	
	List<Virtual> findByUniversidadId(int universidadId);
}
