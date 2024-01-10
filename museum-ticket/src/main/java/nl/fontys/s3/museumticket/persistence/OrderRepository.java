package nl.fontys.s3.museumticket.persistence;

import nl.fontys.s3.museumticket.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select o from OrderEntity as o where o.id = ?1")
    OrderEntity findOrderById(Long id);
    List<OrderEntity> findAllByCustomer_IdOrderByIdDesc(Long customerId);
    OrderEntity findFirstByCustomer_IdOrderByIdDesc(Long customerId);
    @Query("SELECT t.name, m.name AS museumName, SUM(oi.amount) AS totalAmount\n" +
            "FROM OrderItemEntity AS oi\n" +
            "JOIN TicketEntity AS t ON oi.ticket.id = t.id\n" +
            "JOIN OrderEntity AS o ON oi.order.id = o.id\n" +
            "JOIN MuseumEntity AS m ON t.museumEntity.id = m.id\n" +
            "WHERE o.orderDate >= :start AND o.orderDate < :end \n" +
            "GROUP BY t.name, m.name\n" +
            "ORDER BY totalAmount DESC")
    List<Object[]> getTop10MostSoldTicketForPastXMonths(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
