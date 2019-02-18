package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {

}
