package com.challenger.projeto.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenger.projeto.entities.Buses;
import com.challenger.projeto.entities.PointsSupport;
import com.challenger.projeto.services.BusService;
@RestController
@RequestMapping(value="/api/bus")
public class BusResources {
	
	//retorna todos onibus
	@GetMapping(value="/all")
	public ResponseEntity<List<Buses>> findAll(){
		List<Buses>list=BusService.findAll();
		return ResponseEntity.ok().body(list);
	}
	//Retornar onibus mapeado por ID
	@GetMapping(value="/{id}")
	public ResponseEntity<String> findById(@PathVariable long id){
		return ResponseEntity.ok().body(BusService.findByID(id).toStringSolo());
	}
	//Retornar Array de pontos
	@GetMapping(value="/{id}/getPoints")
	public ResponseEntity<String> findPointsById(@PathVariable long id){ 
		StringBuilder build=new StringBuilder();
		Buses b1=BusService.findByID(id);
		build.append("Buses points for id = "+b1.getId()+b1.getPoints().toString());
		return ResponseEntity.ok().body(build.substring(0));
	}
	//Inserir Onibus
	@PostMapping
	public void InsertBus(@RequestBody Buses bus) {
		BusService.insertBus(bus.getName(), bus.getTripFare());
	}
	//Inserir Points
	@PostMapping(value="/{id}/setPoints")
	public void InsertPoints(@RequestBody PointsSupport points,@PathVariable long id) {
		BusService.InsertPointsInBus(id, points);
	}
	

}
