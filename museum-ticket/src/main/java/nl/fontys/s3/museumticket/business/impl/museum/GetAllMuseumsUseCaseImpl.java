package nl.fontys.s3.museumticket.business.impl.museum;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetAllMuseumsUseCase;
import nl.fontys.s3.museumticket.domain.museum.GetAllMuseumsResponse;
import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllMuseumsUseCaseImpl implements GetAllMuseumsUseCase {
  private final MuseumRepository museumRepository;


    @Override
    public GetAllMuseumsResponse getMuseums() {
        List<Museum> museums = museumRepository.findAll()
                .stream()
                .map(MuseumConverter::convert)
                .toList();
        return GetAllMuseumsResponse.builder()
                .museums(museums)
                .build();
    }

    @Override
    public GetAllMuseumsResponse getMuseumsByName(String name) {
        List<MuseumEntity> museums = museumRepository.findByNameContainingIgnoreCase(name);
        List<Museum> result = museums.stream()
                .map(MuseumConverter::convert)
                .toList();
        return GetAllMuseumsResponse.builder()
                .museums(result)
                .build();
    }

}
