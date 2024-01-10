package nl.fontys.s3.museumticket.persistence;

import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("select c from CustomerEntity as c where c.id =?1")
    CustomerEntity findCustomerEntityById(Long id);
}
