package com.challenger.projeto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.challenger.projeto.services.BusService;
import com.challenger.projeto.services.PassengersService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Buses")
public class Buses implements Serializable{
	
	//Serialização para JAVA.IO
	private static final long serialVersionUID = 1L;
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Double tripFare;
	@JsonIgnore
	private PointsSupport points;
	@JsonIgnore
	@OneToMany(mappedBy="bus_id")
	private List<Transaction> transaction=new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy="bus")
	private List<Passengers> listPassagers=new ArrayList<>();
	
	//Construtores
	public Buses(Long id, String name, Double tripFare) {
		this.id = id;
		this.name = name;
		this.tripFare = tripFare;
	}
	public Buses() {
	}
	
	//Getters e Setters
	
	public Long getId() {
		return id;
	}
	public PointsSupport getPoints() {
		return points;
	}
	public void setPoints(PointsSupport points) {
		this.points = points;
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
	public Double getTripFare() {
		return tripFare;
	}
	public void setTripFare(Double tripFare) {
		this.tripFare = tripFare;
	}
	
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public List<Passengers> getListPassagers() {
		return listPassagers;
	}
	
	public void setListPassagers(List<Passengers> listPassagers) {
		this.listPassagers = listPassagers;
	}
	//HasCode e Equals
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
		Buses other = (Buses) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	//toString
	@Override
	public String toString() {
		return "Buses [id=" + id + ", name=" + name + ", tripFare=" + tripFare + ", listPassagers=" + listPassagers
				+ "]\n";
	}
	public String toStringSolo() {
		BusService.getRepository().flush();
		PassengersService.getRepository().flush();
		StringBuilder builder= new StringBuilder();
		builder.append("Bus id:"+id);
		builder.append(" Nome:"+name);
		builder.append(" Valor:"+tripFare);
		return builder.toString();
	}
	
	
}
