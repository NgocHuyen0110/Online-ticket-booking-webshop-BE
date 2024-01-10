package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.museum.GetAllMuseumsResponse;


public interface GetAllMuseumsUseCase {
    GetAllMuseumsResponse getMuseums();
    GetAllMuseumsResponse getMuseumsByName(String name);
}
