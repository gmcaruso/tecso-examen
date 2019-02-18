package coop.tecso.examen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.model.Movement;
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
	public List<MovementDto> listAccounts() {
		
		List<MovementDto> result = new ArrayList<>();
		for (Movement entity : movementRepository.findAll()) {
			MovementDto dto = new MovementDto();
			dto.setId(entity.getId());
			dto.setAsociatedAccount(entity.getAsociatedAccount());
			dto.setDate(entity.getDate());
			dto.setDescription(entity.getDescription());
			dto.setTypeOfMovement(entity.getTypeOfMovement());
			dto.setValue(entity.getAmount());
			result.add(dto);
		}
		
	    return result;
	}
	
	// Add movement to account
	@PostMapping("/agregar-movimiento")
	public void addMovementTo(@RequestParam("accountId") Long accountId, Movement movement) {
		Optional<Account> account = accountRepository.findById(accountId);

		movement.setAsociatedAccount(account.get());
		Movement savedMovement = movementRepository.save(movement);
		
		account.get().getMovements().add(savedMovement);
		accountRepository.save(account.get());
	}
	
	

}
