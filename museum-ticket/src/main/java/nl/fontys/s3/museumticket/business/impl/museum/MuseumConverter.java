package nl.fontys.s3.museumticket.business.impl.museum;


import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;


public class MuseumConverter {
    private MuseumConverter() {
    }

    public static Museum convert(MuseumEntity museumEntity){
        return Museum.builder()
                .id(museumEntity.getId())
                .name(museumEntity.getName())
                .location(museumEntity.getLocation())
                .phone(museumEntity.getPhone())
                .description(museumEntity.getDescription())
                .build();
    }
}
