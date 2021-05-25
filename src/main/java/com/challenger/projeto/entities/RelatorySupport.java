package com.challenger.projeto.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.challenger.projeto.services.BusService;
import com.challenger.projeto.services.PassengersService;
import com.challenger.projeto.services.RelatoryAndTransactionService;
import com.fasterxml.jackson.annotation.JsonIgnore;
//Classe que é o OBJETO de manipulação nos relatórios, onde cada RELATORY tem um List de RelatorySuppport
//Annotation usada porque o Relatory vai ter ela incorporada
@Embeddable
public class RelatorySupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Atributos
	private Long relatorySupport_id;
	private Long bus_id;
	private Double totalAmount=0.0;
	private Integer numTransactions=0;
	private Integer numUsers=0;
	private Date relatory;
	@JsonIgnore
	private Long passenger_id;
	//Construtores
	public RelatorySupport() {
	}
	
	public RelatorySupport(Long bus_id, Double totalAmount, Integer numTransactions, Integer numUsers,
			Long passenger_id,Date date) {
		this.relatory=date;
		this.bus_id = bus_id;
		this.totalAmount = totalAmount;
		this.numTransactions = numTransactions;
		this.numUsers = numUsers;
		this.passenger_id = passenger_id;
	}

	//Getters e Setters
	
	public Passengers getPassenger_id() {
		return PassengersService.findByID(passenger_id);
	}


	public Long getRelatorySupport_id() {
		return relatorySupport_id;
	}

	public void setRelatorySupport_id(Long relatorySupport_id) {
		this.relatorySupport_id = relatorySupport_id;
	}

	public Relatory getRelatory() {
		return RelatoryAndTransactionService.searchRelatoryByDate(relatory);
	}

	public void setRelatory(Relatory relatory) {
		this.relatory = relatory.getMoment();
	}

	public void setPassenger_id(Passengers passenger_id) {
		this.passenger_id = passenger_id.getId();
	}


	public Buses getBus_id() {
		return BusService.findByID(bus_id);
	}
	public void setBus_id(Buses bus_id) {
		this.bus_id = bus_id.getId();
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getNumTransactions() {
		return numTransactions;
	}
	public void setNumTransactions(Integer numTransactions) {
		this.numTransactions = numTransactions;
	}
	public Integer getNumUsers() {
		return numUsers;
	}
	public void setNumUsers(Integer numUsers) {
		this.numUsers = numUsers;
	}
	//HashCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus_id == null) ? 0 : bus_id.hashCode());
		result = prime * result + ((numTransactions == null) ? 0 : numTransactions.hashCode());
		result = prime * result + ((numUsers == null) ? 0 : numUsers.hashCode());
		result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
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
		RelatorySupport other = (RelatorySupport) obj;
		if (bus_id == null) {
			if (other.bus_id != null)
				return false;
		} else if (!bus_id.equals(other.bus_id))
			return false;
		if (numTransactions == null) {
			if (other.numTransactions != null)
				return false;
		} else if (!numTransactions.equals(other.numTransactions))
			return false;
		if (numUsers == null) {
			if (other.numUsers != null)
				return false;
		} else if (!numUsers.equals(other.numUsers))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		return true;
	}
	//ToStrings
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("bus_id: "+bus_id+"\n");
		builder.append("totalAmount: "+totalAmount+"\n");
		builder.append("num_Transactions: "+numTransactions+"\n");
		builder.append("num_Users: "+numUsers+"\n");
		return builder.substring(0);
	}
	
	public String toStringSolo() {
		StringBuilder builder=new StringBuilder();
		builder.append("\nbus_id: "+bus_id+"\n");
		builder.append("totalAmount: "+totalAmount+"\n");
		builder.append("num_Transactions: "+numTransactions+"\n");
		builder.append("num_Users: "+numUsers);
		return builder.substring(0);
	}
	
	
	
	
	
	

}
