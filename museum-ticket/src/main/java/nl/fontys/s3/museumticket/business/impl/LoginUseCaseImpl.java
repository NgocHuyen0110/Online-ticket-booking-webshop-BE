package nl.fontys.s3.museumticket.business.impl;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.AccessTokenEncoder;
import nl.fontys.s3.museumticket.business.LoginUseCase;
import nl.fontys.s3.museumticket.business.exception.InvalidCredentialsException;
import nl.fontys.s3.museumticket.domain.AccessToken;
import nl.fontys.s3.museumticket.domain.LoginRequest;
import nl.fontys.s3.museumticket.domain.LoginResponse;
import nl.fontys.s3.museumticket.persistence.UserRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long customer = user.getCustomer() != null ? user.getCustomer().getId() : null;
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();
        AdminEntity admin = user.getAdmin();

       // Long adminId = admin != null ? admin.getId() : null;
        Long adminId = user.getAdmin() != null ? user.getAdmin().getId():null;
        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .customerId(customer)
                        .adminId(adminId)
                        .build());
    }

}
