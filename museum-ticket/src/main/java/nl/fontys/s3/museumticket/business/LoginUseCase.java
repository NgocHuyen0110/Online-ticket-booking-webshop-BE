package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.LoginRequest;
import nl.fontys.s3.museumticket.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
