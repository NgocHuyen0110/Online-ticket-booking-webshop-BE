package nl.fontys.s3.museumticket.persistence;

import nl.fontys.s3.museumticket.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("select m from TicketEntity as m where m.museumEntity.id =?1")
    List<TicketEntity> findAllByMuseumId(Long id);
    @Query("select t from TicketEntity  as t where t.id = ?1")
    TicketEntity    findTicketById(Long id);
}
