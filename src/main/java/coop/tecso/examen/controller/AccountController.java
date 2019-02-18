package coop.tecso.examen.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	// Create account
	@PostMapping(path = "/crear-cuenta", consumes = "application/json", produces = "application/json")
	public void createAccount(@RequestBody Account account) {
		accountRepository.save(account);
	}

}
