package com.challenger.projeto.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenger.projeto.entities.Buses;
import com.challenger.projeto.entities.Passengers;
import com.challenger.projeto.entities.Relatory;
import com.challenger.projeto.entities.RelatorySupport;
import com.challenger.projeto.entities.Transaction;
import com.challenger.projeto.repositorys.RelatoryRepository;
import com.challenger.projeto.repositorys.TransactionRepository;
//Classe que recebe requisições e envia respostas do tipo transaction e relatory
@Service
public class RelatoryAndTransactionService {
	@Autowired
	private static TransactionRepository repository;
	@Autowired
	private static RelatoryRepository relatoryRepository;
	
	//Métodos de pesquisas nos repositórios 
	public static List<Transaction> findAll() {
		return repository.findAll();
	}
	public static List<Transaction> findAllByIdBusAndDate(Long id,Date data) {
		return repository.findNumberUsersById(BusService.findByID(id), data);
	}
	public static Relatory searchRelatoryByDate(Date date) {
		return relatoryRepository.findByDate(date);
	}

	public static Transaction findById(Long id) {
		return repository.findById(id).get();
	}

	public static List<Relatory> searchAllRelatorysByDateMinAndMax(String data1,String data2) {
		return relatoryRepository.findDatesByDataMinAndMax(formatDate(data1), formatDate(data2));
	}
	//Salva Uma Transaction e depois Chama o metodo para atualizar o relatório
	public static void newTransaction(String date, Long bus, Long passenger) {
		Passengers pass = PassengersService.findByID(passenger);
		Buses buss = BusService.findByID(bus);
		Date data = formatDate(date);
		Transaction transaction = new Transaction(null, data, pass, buss);
		repository.save(transaction);
		SaveRelatory(transaction);
	}
	//Metodo que salva e atualiza relatório, chamado logo apos o newTransaction
	public static void SaveRelatory(Transaction transaction) {
		if(relatoryRepository.findByDate(transaction.getMoment())!=null) {
			Relatory relatory=relatoryRepository.findByDate(transaction.getMoment());
			RelatorySupport relatorySupport=new RelatorySupport(transaction.getBus().getId(), null, 1,1, transaction.getPassenger().getId(),relatory.getMoment());
			relatory.AddRelatory(relatorySupport,relatory);
			relatoryRepository.save(relatory);
		}else {
			Relatory relatory=new Relatory(null,transaction.getMoment());
			RelatorySupport relatorySupport=new RelatorySupport(transaction.getBus().getId(), null, 1,1, transaction.getPassenger().getId(),relatory.getMoment());
			relatory.AddRelatory(relatorySupport,relatory);
			relatoryRepository.save(relatory);
		}
	}
	// Metodos auxiliares
	public static Date formatDate(String date) {
		String[] dataOne = date.split("-");
		for (String s : dataOne) {
			if ("0".equals(s.charAt(0))) {
				s.replaceAll("0", "");
			}
		}
		int year = Integer.valueOf(dataOne[0]);
		int month = Integer.valueOf(dataOne[1]);
		int day = Integer.valueOf(dataOne[2]);
		Date data1 = new Date(year - 1900, month - 1, day);
		return data1;
	}

	private static boolean countNumberUser(Passengers pass, List<Transaction> transaction) {
		for (Transaction t : transaction) {
			Passengers p = t.getPassenger();
			if (p.getId().equals(pass.getId()))
				return true;
		}
		return false;
	}

	

	public static Double calcValAmount(Buses bus, Passengers pass) {
		switch (pass.getType()) {
		case REGULAR:
			return bus.getTripFare();
		case STUDENT:
			return (bus.getTripFare()) / 2;
		case ELDERLY:
			return 0.0;
		}
		return null;
	}
	//Getters e Setters para Repository
	public static TransactionRepository getRepository() {
		return repository;
	}

	public static void setRepository(TransactionRepository repository) {
		RelatoryAndTransactionService.repository = repository;
	}
	public static void setRepositoryRelatory(RelatoryRepository relRepository) {
		RelatoryAndTransactionService.relatoryRepository = relRepository;
	}
}
