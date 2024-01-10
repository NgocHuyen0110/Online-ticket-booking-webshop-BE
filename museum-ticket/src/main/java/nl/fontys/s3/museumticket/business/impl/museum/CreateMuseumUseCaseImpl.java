package nl.fontys.s3.museumticket.business.impl.museum;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateMuseumUseCase;
import nl.fontys.s3.museumticket.business.exception.MuseumNameAlreadyExist;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumRequest;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumResponse;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateMuseumUseCaseImpl implements CreateMuseumUseCase {
    private final MuseumRepository museumRepository;
    @Override
    public CreateMuseumResponse createMuseum(CreateMuseumRequest request) {
        if(museumRepository.findMuseumByName(request.getName()) != null){
            throw new MuseumNameAlreadyExist();
        }

        MuseumEntity museumEntity = saveNewMuseum(request);
        return CreateMuseumResponse.builder()
                .museumId(museumEntity.getId())
                .build();
    }

    public MuseumEntity saveNewMuseum(CreateMuseumRequest request){
        MuseumEntity  newMuseum =MuseumEntity.builder()
                .name(request.getName())
                .location(request.getLocation())
                .phone(request.getPhone())
                .description(request.getDescription())
                .build();
        return  museumRepository.save(newMuseum);
    }
}
