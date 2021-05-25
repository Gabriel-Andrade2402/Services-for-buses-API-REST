package com.challenger.projeto.services;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenger.projeto.entities.Passengers;
import com.challenger.projeto.entities.Relatory;
import com.challenger.projeto.entities.Passengers.TYPE;
import com.challenger.projeto.repositorys.PassengersRepository;

@Service
public class PassengersService {

	// Repositório com dependêcia
	@Autowired
	private static PassengersRepository repository;

	// Retorar Todos Onibus
	public static List<Passengers> findAll() {
		repository.flush();
		return repository.findAll();
	}

	// retorna por id
	public static Passengers findByID(Long id) {
		repository.flush();
		return repository.findById(id).get();
	}

	// Inserir passageiro
	public static void insertPassenger(String name, TYPE type) {
		if(repository!=null)repository.flush();
		Passengers p1 = new Passengers(null, name, type, null);
		repository.save(p1);
	}
	//Getters e Set
		public static PassengersRepository getRepository() {
			return repository;
		}

		public static void setRepository(PassengersRepository repository) {
			PassengersService.repository = repository;
		}
}
