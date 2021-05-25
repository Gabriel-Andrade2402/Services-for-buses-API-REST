package com.challenger.projeto.repositorys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.challenger.projeto.entities.Relatory;

@Component
public interface RelatoryRepository extends JpaRepository<Relatory,Long>{
	@Query(value = "SELECT * FROM Relatory WHERE moment = :data1 ", nativeQuery=true)
	Relatory findByDate(@Param("data1") Date data1);
	
	@Query(value="SELECT * FROM Relatory WHERE moment >= :data1 AND moment <= :data2", nativeQuery=true)
	List<Relatory> findDatesByDataMinAndMax(@Param("data1")Date data1,@Param("data2") Date data2);
	
}
