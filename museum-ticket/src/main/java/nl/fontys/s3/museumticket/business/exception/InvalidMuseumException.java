package nl.fontys.s3.museumticket.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidMuseumException extends ResponseStatusException {
    public InvalidMuseumException(String errorCode){ super(HttpStatus.BAD_REQUEST, errorCode);}

}
