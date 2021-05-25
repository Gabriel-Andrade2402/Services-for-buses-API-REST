package com.challenger.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenger.projeto.entities.Buses;
import com.challenger.projeto.entities.PointsSupport;
import com.challenger.projeto.repositorys.BusesRepository;

@Service
public class BusService {
	//Repositório com dependêcia
	@Autowired
	private static BusesRepository repository;
	//Retorar Todos Onibus
	public static List<Buses> findAll(){
		repository.flush();
		return repository.findAll();
	}
	//retorna por id
	public static Buses findByID(Long id) {
		repository.flush();
		return repository.findById(id).get();
	}
	//Inserir Ónibus
	public static void insertBus(String name,Double value) {
		Buses b1=new Buses(null, name, value);
		repository.save(b1);
	}
	//Inseris pontos no Ónibus
	public static void InsertPointsInBus(long id,PointsSupport points) {
		Buses b1=BusService.findByID(id);
		b1.setPoints(points);
		repository.save(b1);
	}
	
	//Getters e Set
	public static BusesRepository getRepository() {
		return repository;
	}

	public static void setRepository(BusesRepository repository) {
		BusService.repository = repository;
	}
	
	
}
