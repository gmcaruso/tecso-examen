package coop.tecso.examen.dto;

import java.io.Serializable;
import java.util.List;

import coop.tecso.examen.model.Movement;

public class AccountDto implements Serializable{

	private static final long serialVersionUID = 7653168659400121374L;
	
	private Long id;
	private Long accountNumber;
	private String currency;
	private Long balance;
	private List<Movement> movements;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public List<Movement> getMovements() {
		return movements;
	}
	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
}
