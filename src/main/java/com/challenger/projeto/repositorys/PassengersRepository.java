package com.challenger.projeto.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.challenger.projeto.entities.Passengers;

@Component
public interface PassengersRepository extends JpaRepository<Passengers,Long> {

}
