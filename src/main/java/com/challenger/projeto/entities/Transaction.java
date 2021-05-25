package com.challenger.projeto.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.challenger.projeto.services.PassengersService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Transaction")
public class Transaction implements Serializable {

	
	private static final long serialVersionUID = 1L;

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_transaction;

	// Atributos usados apenas para receber um modelo de transaction no formato JSON
	// da requisição
	@Transient
	private String data_con;
	@Transient
	private Long idBus_con;
	@Transient
	private Long idPass_con;
	// Usados mais tarde por model logo não devem ser removidos

	@Column(nullable = false)
	private Date moment;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "passenger_id", nullable = false)
	private Passengers passenger_id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "bus_id", nullable = false)
	private Buses bus_id;

	// Construtores
	public Transaction(Long id_transaction, Date moment, Passengers passenger, Buses bus) {
		this.id_transaction = id_transaction;
		this.moment = moment;
		this.passenger_id = passenger;
		this.bus_id = bus;
	}

	public Transaction() {
	}

	// Getters e Setters

	public Long getId_transaction() {
		return id_transaction;
	}

	public String getData_con() {
		return data_con;
	}

	public void setData_con(String data_con) {
		this.data_con = data_con;
	}

	public Long getIdBus_con() {
		return idBus_con;
	}

	public void setIdBus_con(Long idBus_con) {
		this.idBus_con = idBus_con;
	}

	public Long getIdPass_con() {
		return idPass_con;
	}

	public void setIdPass_con(Long idPass_con) {
		this.idPass_con = idPass_con;
	}

	public Passengers getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(Passengers passenger_id) {
		this.passenger_id = passenger_id;
	}

	public void setId_transaction(Long id_transaction) {
		this.id_transaction = id_transaction;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Passengers getPassenger() {
		return passenger_id;
	}

	public Buses getBus() {
		return bus_id;
	}

	public Buses getBus_id() {
		return bus_id;
	}

	public void setBus_id(Buses bus_id) {
		this.bus_id = bus_id;
	}

	// HashCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus_id == null) ? 0 : bus_id.hashCode());
		result = prime * result + ((moment == null) ? 0 : moment.hashCode());
		result = prime * result + ((passenger_id == null) ? 0 : passenger_id.hashCode());
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
		Transaction other = (Transaction) obj;
		if (bus_id == null) {
			if (other.bus_id != null)
				return false;
		} else if (!bus_id.equals(other.bus_id))
			return false;
		if (moment == null) {
			if (other.moment != null)
				return false;
		} else if (!moment.equals(other.moment))
			return false;
		if (passenger_id == null) {
			if (other.passenger_id != null)
				return false;
		} else if (!passenger_id.equals(other.passenger_id))
			return false;
		return true;
	}

	// ToString
	@Override
	public String toString() {
		return "Transaction [id_transaction=" + id_transaction + ", moment=" + moment + ", passenger_id=" + passenger_id.getId()
				+ ",\n bus_id=" + bus_id.getId() + "]\n";
	}
	

}
