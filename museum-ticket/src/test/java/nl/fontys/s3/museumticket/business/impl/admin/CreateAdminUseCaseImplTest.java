package nl.fontys.s3.museumticket.business.impl.admin;

import nl.fontys.s3.museumticket.business.exception.EmailAlreadyExistsException;
import nl.fontys.s3.museumticket.domain.AccessToken;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminRequest;
import nl.fontys.s3.museumticket.domain.admin.CreateAdminResponse;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.UserRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import nl.fontys.s3.museumticket.persistence.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAdminUseCaseImplTest {
    @Mock
    private AdministrationRepository administrationRepositoryMock;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateAdminUseCaseImpl createAdminUseCase;
    @Test
    void createAdmin_ValidRequest(){
        CreateAdminRequest request =  new CreateAdminRequest().builder()
                .fullName("Thai Thi Ngoc Huyen")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .email("huyenAdmin@gmail.com")
                .password("123")
                .build();
        // set new admin
        AdminEntity newAdmin = new AdminEntity().builder()
                .fullName(request.getFullName())
                .dob(request.getDob())
                .address(request.getAddress())
                .build();
        UserEntity userEntity = new UserEntity().builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .admin(newAdmin)
                .build();
        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(userEntity)
                        .role(RoleEnum.ADMIN)
                        .build()));
        //set saved admin
        AdminEntity savedAdmin = new AdminEntity().builder()
                .id(1L)
                .fullName(request.getFullName())
                .dob(request.getDob())
                .address(request.getAddress())
                .build();
        UserEntity userEntity1 = new UserEntity().builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .admin(savedAdmin)
                .build();
        userEntity1.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(userEntity1)
                        .role(RoleEnum.ADMIN)
                        .build()));
        when(userRepository.findByEmail("huyenAdmin@gmail.com")).thenReturn(null);
        when(administrationRepositoryMock.save(newAdmin)).thenReturn(savedAdmin);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded password");
        CreateAdminResponse actual = createAdminUseCase.createAdmin(request);
        CreateAdminResponse expect = CreateAdminResponse.builder().adminId(1L).build();
        assertEquals(actual,expect);
        verify(userRepository).findByEmail("huyenAdmin@gmail.com");
        verify(administrationRepositoryMock).save(newAdmin);
        verify(passwordEncoder).encode(request.getPassword());
    }
    @Test
    void createAdmin_ExistEmail(){
        CreateAdminRequest request =  new CreateAdminRequest().builder()
                .fullName("Thai Thi Ngoc Huyen")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .email("huyenAdmin@gmail.com")
                .password("123")
                .build();
        //set saved admin
        AdminEntity savedAdmin = new AdminEntity().builder()
                .id(1L)
                .fullName(request.getFullName())
                .dob(request.getDob())
                .address(request.getAddress())
                .build();
        UserEntity userEntity1 = new UserEntity().builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .admin(savedAdmin)
                .build();
        userEntity1.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(userEntity1)
                        .role(RoleEnum.ADMIN)
                        .build()));
        when(userRepository.findByEmail("huyenAdmin@gmail.com")).thenReturn(userEntity1);
        assertThrows(EmailAlreadyExistsException.class, ()-> createAdminUseCase.createAdmin(request));
        verify(userRepository).findByEmail("huyenAdmin@gmail.com");
        verifyNoMoreInteractions(userRepository);

    }
}