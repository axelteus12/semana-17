package com.universidad.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.service.entity.Universidad;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Integer>{

}
