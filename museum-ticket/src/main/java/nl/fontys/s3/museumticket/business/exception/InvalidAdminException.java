package nl.fontys.s3.museumticket.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAdminException extends ResponseStatusException {
    public InvalidAdminException(String errorCode){ super(HttpStatus.BAD_REQUEST, errorCode);}
}
