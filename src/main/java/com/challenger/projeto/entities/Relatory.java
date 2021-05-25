package com.challenger.projeto.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.challenger.projeto.services.PassengersService;
import com.challenger.projeto.services.RelatoryAndTransactionService;

@Entity
@Table(name="Relatory")
public class Relatory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_Relatory;
	
	@Column(name="moment")
	private Date moment;
	
	@ElementCollection
    @CollectionTable(name = "Collection_list", joinColumns = @JoinColumn(name = "Relatorys"))
    @Column(name = "list_Relatorys")
	private List<RelatorySupport> list_Relatoryes=new ArrayList<>();
	
	public Relatory(Long id_Relatory, Date moment) {
		this.id_Relatory = id_Relatory;
		this.moment = moment;
	}

	public Relatory() {
	}
	
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Long getId_Relatory() {
		return id_Relatory;
	}

	public void setId_Relatory(Long id_Relatory) {
		this.id_Relatory = id_Relatory;
	}

	public List<RelatorySupport> getList_Relatoryes() {
		return list_Relatoryes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_Relatory == null) ? 0 : id_Relatory.hashCode());
		result = prime * result + ((list_Relatoryes == null) ? 0 : list_Relatoryes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relatory other = (Relatory) obj;
		if (id_Relatory == null) {
			if (other.id_Relatory != null)
				return false;
		} else if (!id_Relatory.equals(other.id_Relatory))
			return false;
		if (list_Relatoryes == null) {
			if (other.list_Relatoryes != null)
				return false;
		} else if (!list_Relatoryes.equals(other.list_Relatoryes))
			return false;
		return true;
	}

	public String toStringPesonalizado() {
		StringBuilder builder=new StringBuilder();
		builder.append(moment+":[\n");
		for(RelatorySupport relatorySup:list_Relatoryes) {
			builder.append(relatorySup.toStringSolo());
		}
		builder.append("]");
		return builder.substring(0);
	}
	//Checar Existencia 
	public static boolean checkExisting(RelatorySupport newRelatory,Relatory relatory) {
		for(RelatorySupport rs:relatory.getList_Relatoryes()) {
			if(rs.getBus_id().getId().equals(newRelatory.getBus_id().getId()))return true;
		}
		return false;
	}
	//Retornar RelatorySupport Existente
	public static RelatorySupport returnRelatoryExisting(RelatorySupport newRelatory,Relatory relatory) {
		for(RelatorySupport rs:relatory.getList_Relatoryes()) {
			if(rs.getBus_id().getId().equals(newRelatory.getBus_id().getId()))return rs;
		}
		return newRelatory;
	}
	//Update List
	public static void updateList(RelatorySupport newRelatory,Relatory relatory) {
		for(RelatorySupport rs:relatory.getList_Relatoryes()) {
			if(rs.getBus_id().getId().equals(newRelatory.getBus_id().getId())) {
				rs.setBus_id(newRelatory.getBus_id());
				rs.setNumTransactions(newRelatory.getNumTransactions());
				rs.setNumUsers(newRelatory.getNumUsers());
				rs.setPassenger_id(newRelatory.getPassenger_id());
				rs.setTotalAmount(newRelatory.getTotalAmount());
			}
		}
	}
	//Adicionar RelatorySupport na List
	public static void AddRelatory(RelatorySupport newRelatory,Relatory relatory) {
		if(checkExisting(newRelatory,relatory)) {
			RelatorySupport rs=returnRelatoryExisting(newRelatory,relatory);
			rs.setNumTransactions(rs.getNumTransactions()+1);
			rs.setTotalAmount(rs.getTotalAmount()+calcTicket(newRelatory.getPassenger_id(),rs.getBus_id()));
			rs.setNumUsers(findNumUsersBybus_id(newRelatory.getBus_id().getId(),relatory.getMoment()));
			updateList(rs,relatory);
		}else {
			newRelatory.setTotalAmount(calcTicket(newRelatory.getPassenger_id(),newRelatory.getBus_id()));
			newRelatory.setNumUsers(findNumUsersBybus_id(newRelatory.getBus_id().getId(),relatory.getMoment()));
			relatory.getList_Relatoryes().add(newRelatory);
		}
	}
	//Caculcular Valor a pagar
	public static Double calcTicket(Passengers pass,Buses bus) {
		Passengers passenger=PassengersService.findByID(pass.getId());
		switch (passenger.getType()) {
		case REGULAR:
			return bus.getTripFare();
		case ELDERLY:
			return 0.0;
		case STUDENT:
			return (bus.getTripFare())/2;
		}
		return null;
	}
	//Procura o número de usuarios baseado no id do ónibus
	public static Integer findNumUsersBybus_id(Long id,Date data) { 
		List<Transaction> list=RelatoryAndTransactionService.findAllByIdBusAndDate(id, data);
		Set<Long> setArray=new HashSet<>();
		for(Transaction t:list) {
			setArray.add(t.getPassenger_id().getId());
		}
		return setArray.size();
	}
	
}
