package nl.fontys.s3.museumticket.persistence;

import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrationRepository extends JpaRepository<AdminEntity, Long> {
}
