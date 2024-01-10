package nl.fontys.s3.museumticket.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.LoginUseCase;
import nl.fontys.s3.museumticket.domain.LoginRequest;
import nl.fontys.s3.museumticket.domain.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000/")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}