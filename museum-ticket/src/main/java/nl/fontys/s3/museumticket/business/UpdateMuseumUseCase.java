package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.museum.UpdateMuseumRequest;

public interface UpdateMuseumUseCase {
    void updateMuseum(UpdateMuseumRequest request);
}
