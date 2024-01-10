package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.museum.CreateMuseumRequest;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumResponse;

public interface CreateMuseumUseCase {
    CreateMuseumResponse createMuseum(CreateMuseumRequest request);
}
