package br.com.axys.certgen.repository;

import br.com.axys.certgen.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClassRepository extends JpaRepository<ClassEntity, UUID> {
}