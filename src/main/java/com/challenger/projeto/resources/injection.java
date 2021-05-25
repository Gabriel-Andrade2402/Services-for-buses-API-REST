package com.challenger.projeto.resources;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.challenger.projeto.entities.Buses;
import com.challenger.projeto.repositorys.BusesRepository;
import com.challenger.projeto.repositorys.PassengersRepository;
import com.challenger.projeto.repositorys.RelatoryRepository;
import com.challenger.projeto.repositorys.TransactionRepository;
import com.challenger.projeto.services.BusService;
import com.challenger.projeto.services.PassengersService;
import com.challenger.projeto.services.RelatoryAndTransactionService;
@Service
public class injection implements ApplicationRunner{
	
	//Auto injeção de dados sem utilização do spring injection.
	@Autowired
	private BusesRepository busRepository;
	@Autowired
	private PassengersRepository passRepository;
	@Autowired
	private TransactionRepository tranRepository;
	@Autowired
	private RelatoryRepository relRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Buses b1 = new Buses(null, "Metro sacomã", 4.40);
		Buses b2 = new Buses(null, "vila olimpia", 3.80);
		Buses b3 = new Buses(null, "Itaim Paulista", 5.45);
		busRepository.saveAll(Arrays.asList(b1, b2, b3));
		PassengersService.setRepository(passRepository);
		RelatoryAndTransactionService.setRepository(tranRepository);
		RelatoryAndTransactionService.setRepositoryRelatory(relRepository);
		BusService.setRepository(busRepository);
	}
}
