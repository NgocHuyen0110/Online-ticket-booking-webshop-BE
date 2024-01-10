package nl.fontys.s3.museumticket.business.impl.admin;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateAdminUseCase;
import nl.fontys.s3.museumticket.business.exception.EmailAlreadyExistsException;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminRequest;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminResponse;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.UserRepository;
import nl.fontys.s3.museumticket.persistence.entity.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreateAdminUseCaseImpl implements CreateAdminUseCase {
    private final AdministrationRepository administrationRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest request) {
        if(userRepository.findByEmail(request.getEmail())!=null){
            throw new EmailAlreadyExistsException();
        }
        AdminEntity saveAdmin = saveNewAdmin(request);
        saveNewUser(saveAdmin, request.getPassword(), request.getEmail());
        return CreateAdminResponse.builder()
                .adminId(saveAdmin.getId())
                .build();
    }
    private void saveNewUser(AdminEntity adminEntity, String password,String email){
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity newUser = UserEntity.builder()
                .email(email)
                .password(encodedPassword)
                .admin(adminEntity)
                .build();
        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.ADMIN)
                        .build()));
        userRepository.save(newUser);
    }
    private AdminEntity saveNewAdmin(CreateAdminRequest request){
        AdminEntity admin = AdminEntity.builder()
                .fullName(request.getFullName())
                .dob(request.getDob())
                .address(request.getAddress())
                .build();
        return administrationRepository.save(admin);
    }
}
