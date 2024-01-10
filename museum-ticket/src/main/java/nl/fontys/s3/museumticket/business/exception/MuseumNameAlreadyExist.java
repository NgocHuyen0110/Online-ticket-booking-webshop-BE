package nl.fontys.s3.museumticket.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MuseumNameAlreadyExist extends ResponseStatusException {
    public MuseumNameAlreadyExist() {
        super(HttpStatus.BAD_REQUEST, "NAME_ALREADY_EXISTS");
    }
}
