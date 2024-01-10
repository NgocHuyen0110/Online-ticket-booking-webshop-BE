package nl.fontys.s3.museumticket.business.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import nl.fontys.s3.museumticket.business.AccessTokenEncoder;
import nl.fontys.s3.museumticket.business.exception.InvalidCredentialsException;
import nl.fontys.s3.museumticket.domain.LoginRequest;
import nl.fontys.s3.museumticket.domain.LoginResponse;
import nl.fontys.s3.museumticket.persistence.UserRepository;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import nl.fontys.s3.museumticket.persistence.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
public class LoginUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    public void testLogin_InvalidCredentials_ExceptionThrown() {
        String email = "test@example.com";
        String password = "password";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword("encodedPassword");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(passwordEncoder.matches(password, userEntity.getPassword())).thenReturn(false);
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches(password, userEntity.getPassword());
        verifyNoMoreInteractions(userRepository);
    }
}
