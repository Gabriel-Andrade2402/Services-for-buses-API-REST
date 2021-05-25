package com.challenger.projeto.repositorys;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.challenger.projeto.entities.Buses;
import com.challenger.projeto.entities.Transaction;

@Component
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	@Query(value = "SELECT * FROM Transaction WHERE moment >= :data1 AND moment <= :data2", nativeQuery=true)
	List<Transaction> findByDateminAndMax(@Param("data1") Date data1,@Param("data2") Date data2);
	
	@Query(value="SELECT * FROM Transaction WHERE bus_id = :objBus AND moment = :moment", nativeQuery=true)
	List<Transaction> findNumberUsersById(@Param("objBus")Buses objBus,@Param("moment")Date moment );
	
}
