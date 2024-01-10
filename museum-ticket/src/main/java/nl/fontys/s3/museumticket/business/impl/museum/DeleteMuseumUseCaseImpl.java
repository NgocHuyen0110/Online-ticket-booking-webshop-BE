package nl.fontys.s3.museumticket.business.impl.museum;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.DeleteMuseumUseCase;
import nl.fontys.s3.museumticket.business.exception.InvalidMuseumException;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteMuseumUseCaseImpl implements DeleteMuseumUseCase {
    private final MuseumRepository museumRepository;
    @Override
    public void deleteMuseum(long id) {
        Optional<MuseumEntity> museumEntity= museumRepository.findById(id);
        if(museumEntity.isEmpty()){
            throw new InvalidMuseumException("MUSEUM_ID_INVALID");
        }
        this.museumRepository.deleteById(id);
    }


}
