package com.challenger.projeto.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.challenger.projeto.entities.Buses;

@Component
public interface BusesRepository extends JpaRepository<Buses,Long> {
	

}
