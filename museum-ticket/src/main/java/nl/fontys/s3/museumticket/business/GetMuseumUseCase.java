package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.museum.Museum;

import java.util.Optional;

public interface GetMuseumUseCase {
    Optional<Museum> getMuseumById(long id);

}
