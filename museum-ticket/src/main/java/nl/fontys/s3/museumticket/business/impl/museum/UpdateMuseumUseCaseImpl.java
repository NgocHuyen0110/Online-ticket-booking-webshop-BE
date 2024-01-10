package nl.fontys.s3.museumticket.business.impl.museum;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.UpdateMuseumUseCase;
import nl.fontys.s3.museumticket.business.exception.InvalidMuseumException;
import nl.fontys.s3.museumticket.business.exception.MuseumNameAlreadyExist;
import nl.fontys.s3.museumticket.domain.museum.UpdateMuseumRequest;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateMuseumUseCaseImpl implements UpdateMuseumUseCase {
    private final MuseumRepository museumRepository;
    @Override
    public void updateMuseum(UpdateMuseumRequest request) {
        Optional<MuseumEntity> museumEntity= museumRepository.findById(request.getId());
        if(museumEntity.isEmpty()){
            throw new InvalidMuseumException("MUSEUM_ID_INVALID");
        }
        if(museumRepository.findMuseumByName(request.getName())!= null){
            throw new MuseumNameAlreadyExist();
        }
        MuseumEntity museum =museumEntity.get();
        updateFields(request, museum);

    }
    private void updateFields(UpdateMuseumRequest request, MuseumEntity museum){
            museum.setName(request.getName());
            museum.setLocation(request.getLocation());
            museum.setPhone(request.getPhone());
            museum.setDescription(request.getDescription());
            museumRepository.save(museum);

    }
}
