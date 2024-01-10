package nl.fontys.s3.museumticket.configuration;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class MuseumDatabaseDataInitializer {
//    private  MuseumRepository museumRepository;
//    @EventListener(ApplicationReadyEvent.class)
//    public void customizeMuseumsData(){
//        if(museumRepository.count() == 0){
//            museumRepository.createMuseum(
//                    MuseumEntity.builder()
//                            .name("Eye FilmMuseum")
//                            .location("IJpromenade 11031 KT Amsterdam")
//                            .phone("020 - 58 91 400")
//                            .build());
//            museumRepository.createMuseum(
//                    MuseumEntity.builder()
//                            .name("Forteiland Pampus")
//                            .location("Fort Pampus 1 1398 PX Muiden")
//                            .phone("0294 - 26 23 26")
//                            .build());
//            museumRepository.createMuseum(
//                    MuseumEntity.builder()
//                            .name("Kasteel de Haar")
//                            .location("Kasteellaan 13455 RR Haarzuilens")
//                            .phone("030 - 67 78 515")
//                            .build());
//        }
//    }
}
