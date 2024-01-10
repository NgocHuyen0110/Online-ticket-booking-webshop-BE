package nl.fontys.s3.museumticket.persistence;

import nl.fontys.s3.museumticket.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
