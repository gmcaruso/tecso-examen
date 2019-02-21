package coop.tecso.examen.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.model.TypeOfMovement;
import coop.tecso.examen.repository.AccountRepository;
import coop.tecso.examen.repository.MovementRepository;

@RestController
@RequestMapping("/movement")
public class MovementController {
	
	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	// Get All movements by account
	@GetMapping("/listar-movimientos")
	public List<MovementDto> listAccounts(@RequestParam("accountId") Long accountId) {
		
		List<MovementDto> result = new ArrayList<>();
		Optional<Account> account = accountRepository.findById(accountId);
		
		if(!account.isPresent()) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "El ID no corresponde a una cuenta existente");
		}
		
		account.get().getMovements().sort(Comparator.comparing(Movement::getDate).reversed());
		for (Movement entity : account.get().getMovements()) {
			MovementDto dto = new MovementDto();
			dto.setId(entity.getId());
			dto.setDate(entity.getDate());
			dto.setDescription(entity.getDescription());
			dto.setTypeOfMovement(entity.getTypeOfMovement());
			dto.setValue(entity.getAmount());
			result.add(dto);
		}
		
	    return result;
	}
	
	// Get one movement
	@GetMapping("/encontrar-movimiento")
	public Movement findMovement(@RequestParam("accountId") Long accountId) {
		Movement entity = movementRepository.findById(accountId).get();
		MovementDto dto = new MovementDto();
		dto.setId(entity.getId());
		dto.setDate(entity.getDate());
		dto.setDescription(entity.getDescription());
		dto.setTypeOfMovement(entity.getTypeOfMovement());
		dto.setValue(entity.getAmount());
	    
		return entity;
	}
		
	
	// Add movement to account
	@PatchMapping("/agregar-movimiento")
	public void addMovementTo(@RequestParam("accountId") Long accountId, @RequestBody Movement movement) {
		Optional<Account> account = accountRepository.findById(accountId);
		
		if(!account.isPresent()) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "El ID no corresponde a una cuenta existente");
		}
		
		this.checkTypeOfMovement(account.get(), movement);
	}
	
	private void checkTypeOfMovement(Account account, Movement movement) {
		if (movement.getTypeOfMovement() == TypeOfMovement.CREDIT ) {
			account.setBalance(account.getBalance() + movement.getAmount());
			this.updateAccount(account, movement);
		}
		else {
			
			switch (account.getCurrency()) {
			case PESOS:
					System.out.println("Pesos");
					this.debit(account, movement, -1000, "ERROR: El debito genera un descubierto mayor a 1000 Pesos");
				break;
				
			case EUROS:
					System.out.println("Euros");
					this.debit(account, movement, -150, "ERROR: El debito genera un descubierto mayor a 150 Euros");
				break;

			default:
					System.out.println("Dolares");
					this.debit(account, movement, -300, "ERROR: El debito genera un descubierto mayor a 300 Dolares");
				break;
			}
		}
		
	}
	
	private void debit (Account account, Movement movement, long amount, String message) {
		if ((account.getBalance() - movement.getAmount()) <= amount) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , message);
		}
		else {
			account.setBalance(account.getBalance() - movement.getAmount());
			
			updateAccount(account, movement);
		}
	}

	private void updateAccount(Account account, Movement movement) {
		Movement savedMovement = movementRepository.save(movement);
		account.getMovements().add(savedMovement);
		accountRepository.save(account);
	}
	
}
