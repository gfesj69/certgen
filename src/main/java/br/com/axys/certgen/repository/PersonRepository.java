package br.com.axys.certgen.repository;

import br.com.axys.certgen.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}