package com.challenger.projeto.resources;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenger.projeto.entities.Relatory;
import com.challenger.projeto.entities.RelatorySupport;
import com.challenger.projeto.entities.Transaction;
import com.challenger.projeto.services.RelatoryAndTransactionService;

@RestController
@RequestMapping("/api")
public class TransactionResources {
	//Mapear Lista completa de Transactions
	@GetMapping(value = "/transaction/all")
	public ResponseEntity<String> findAll() {
		List<Transaction> list = RelatoryAndTransactionService.findAll();
		StringBuilder build=new StringBuilder();
		for(Transaction t:list) {build.append(t.toString());}
		return ResponseEntity.ok().body(build.substring(0));
	}
	//Inserir Transações
	@PostMapping(value="/transaction")
	public ResponseEntity<Void> transaction(@RequestBody Transaction transaction){
		RelatoryAndTransactionService.newTransaction(transaction.getData_con(),transaction.getIdBus_con(),transaction.getIdPass_con());
		return ResponseEntity.noContent().build();
	}
	//Retornar com base em data
	@GetMapping(value="/relatory:startDate={data1}&endDate={data2}")
	public ResponseEntity<String> Relatory(@PathVariable String data1,@PathVariable String data2) {
		StringBuilder build=new StringBuilder();
		for(Relatory relatory:RelatoryAndTransactionService.searchAllRelatorysByDateMinAndMax(data1,data2)) {
			build.append(relatory.getMoment()+"[");
			for(RelatorySupport relatory_supports:relatory.getList_Relatoryes()) {
				build.append(relatory_supports.toStringSolo());
			}
			build.append("]\n");
		}
		return ResponseEntity.ok().body(build.substring(0));
	}
}
