package coop.tecso.examen.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Movement extends AbstractPersistentObject {

	private static final long serialVersionUID = -1618684298473142479L;
	
	private Date date;
	private TypeOfMovement typeOfMovement;
	private String description;
	private long amount;
	
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
	public long getAmount() {
		return amount;
	}
	public void setAmount(long value) {
		this.amount = value;
	}
}
