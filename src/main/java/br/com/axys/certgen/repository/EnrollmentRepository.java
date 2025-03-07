package br.com.axys.certgen.repository;

import br.com.axys.certgen.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
}