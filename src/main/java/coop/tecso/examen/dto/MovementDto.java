package coop.tecso.examen.dto;

import java.io.Serializable;
import java.util.Date;

import coop.tecso.examen.model.TypeOfMovement;

public class MovementDto implements Serializable{
	
	private static final long serialVersionUID = -8135030663106329537L;
	
	private Long id;
	private Date date;
	private TypeOfMovement typeOfMovement;
	private String description;
	private long amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TypeOfMovement getTypeOfMovement() {
		return typeOfMovement;
	}
	public void setTypeOfMovement(TypeOfMovement typeOfMovement) {
		this.typeOfMovement = typeOfMovement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getValue() {
		return amount;
	}
	public void setValue(long value) {
		this.amount = value;
	}
}	
