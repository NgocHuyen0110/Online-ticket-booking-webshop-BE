package nl.fontys.s3.museumticket.persistence;


import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MuseumRepository extends JpaRepository<MuseumEntity, Long> {


    @Query("select m from MuseumEntity as m where m.name =?1")
    MuseumEntity findMuseumByName(String name);
    List<MuseumEntity> findByNameContainingIgnoreCase(String name);


}
