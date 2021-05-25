package com.challenger.projeto.resources;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenger.projeto.entities.Passengers;
import com.challenger.projeto.entities.Relatory;
import com.challenger.projeto.services.PassengersService;

@RestController
@RequestMapping(value = "api/users")
public class PassemgersResources {
	// retorna todos usuarios
	@GetMapping(value = "/all")
	public ResponseEntity<List<Passengers>> findAll() {
		List<Passengers> list = PassengersService.findAll();
		return ResponseEntity.ok().body(list);
	}
	//Retorno por ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Passengers> findById(@PathVariable long id) {
		return ResponseEntity.ok().body(PassengersService.findByID(id));
	}
	//Inserir Passageiro
	@PostMapping
	public void InsertPass(@RequestBody Passengers pass) {
		PassengersService.insertPassenger(pass.getName(), pass.getType());
	}

}
