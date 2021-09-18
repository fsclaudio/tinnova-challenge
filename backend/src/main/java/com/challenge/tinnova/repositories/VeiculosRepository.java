package com.challenge.tinnova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.tinnova.entities.Veiculos;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {
	
}
