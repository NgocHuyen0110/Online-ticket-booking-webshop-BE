package nl.fontys.s3.museumticket.business.impl.museum;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetMuseumUseCase;
import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetMuseumUseCaseImpl implements GetMuseumUseCase {
    private final MuseumRepository museumRepository;

    @Override
    public Optional<Museum> getMuseumById(long id) {
        return museumRepository.findById(id).map(MuseumConverter::convert);
    }

}
