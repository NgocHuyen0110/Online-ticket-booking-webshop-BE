package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.business.exception.InvalidMuseumException;

public interface MuseumNameValidation {
    void validatedName(String museumName) throws InvalidMuseumException;
}
