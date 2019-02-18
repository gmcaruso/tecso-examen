package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
