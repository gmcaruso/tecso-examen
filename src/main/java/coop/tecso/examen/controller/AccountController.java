package coop.tecso.examen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.repository.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	// Get All accounts
	@GetMapping("/listar-cuentas")
	public List<AccountDto> listAccounts() {
		List<AccountDto> result = new ArrayList<>();
		for (Account entity : accountRepository.findAll()) {
			AccountDto dto = new AccountDto();
			dto.setId(entity.getId());
			dto.setAccountNumber(entity.getAccountNumber());
			dto.setBalance(entity.getBalance());
			dto.setCurrency(entity.getCurrency());
			dto.setMovements(entity.getMovements());
			
			result.add(dto);
		}
		
	    return result;
	}
	
	// Get one account
	@GetMapping("/encontrar-cuenta")
	public Account findAccount(@RequestParam("accountId") Long accountId) {
		Account entity = accountRepository.findById(accountId).get();
		AccountDto dto = new AccountDto();
		dto.setId(entity.getId());
		dto.setAccountNumber(entity.getAccountNumber());
		dto.setBalance(entity.getBalance());
		dto.setCurrency(entity.getCurrency());
		dto.setMovements(entity.getMovements());
	    
		return entity;
	}
	
	
	
	// Create account
	@PostMapping(path = "/crear-cuenta", consumes = "application/json")
	public void createAccount(@RequestBody Account account) {
		accountRepository.save(account);
	}

	
	// Delete account
	@DeleteMapping(path = "/eliminar-cuenta")
	public void deleteAccount(@RequestParam("accountId") Long accountId) throws ResponseStatusException  {
		Optional<Account> account = accountRepository.findById(accountId);
		
		if(!account.isPresent()) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "El ID no corresponde a una cuenta existente");
		}
		
		if(account.get().getMovements().size() > 0) {
			throw new ResponseStatusException(
			          HttpStatus.CONFLICT, "La cuenta que intentas eliminar tiene movimientos asociados");
		}
		accountRepository.deleteById(accountId);
	}
}
