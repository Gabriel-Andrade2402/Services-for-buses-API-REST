package com.challenger.projeto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Passengers")
public class Passengers implements Serializable{
	//Serialização para JAVA.IO
	private static final long serialVersionUID = 1L;
	//Tipos De Passageiros
	public static enum TYPE{
		REGULAR,
		ELDERLY,
		STUDENT;
	}
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private TYPE type;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bus_id")
	private Buses bus;
	
	@OneToMany(mappedBy = "passenger_id",cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	//Construtores
	public Passengers() {
	}
	public Passengers(Long id, String name, TYPE type, Buses bus) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.bus = bus;
	}
	//Getters e Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public Buses getBus() {
		return bus;
	}
	public void setBus(Buses bus) {
		this.bus = bus;
	}
	//HashCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Passengers other = (Passengers) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	//toString padrão
	@Override
	public String toString() {
		return "Passengers [id=" + id + ", name=" + name + ", type=" + type + ", bus=" + bus + "]";
	}
	
	
}
