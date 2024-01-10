package nl.fontys.s3.museumticket.business.impl.admin;

import nl.fontys.s3.museumticket.business.exception.UnauthorizedDataAccessException;
import nl.fontys.s3.museumticket.domain.AccessToken;
import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class GetAdminUseCaseImplTest {
    @Mock
    private AdministrationRepository administrationRepositoryMock;
    @Mock
    private AccessToken requestAccessToken;
    @InjectMocks
    private GetAdminUseCaseImpl getAdminUseCase;
    @Test
    void getAdmin_ValidAdminId_MatchingLoggedInCustomer_returnAdmin(){
        AdminEntity admin = AdminEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .build();
        when(requestAccessToken.getAdminId()).thenReturn(1L);
        when(administrationRepositoryMock.findById(1L)).thenReturn(Optional.of(admin));
        Optional<Administration> actual = getAdminUseCase.getAdmin(1L);
        Optional<Administration> expect = Optional.of(Administration.builder()
                .id(admin.getId())
                .fullName(admin.getFullName())
                .dob(admin.getDob())
                .address(admin.getAddress())
                .build()
        );
        assertEquals(expect,actual);
        verify(requestAccessToken).getAdminId();
        verify(administrationRepositoryMock).findById(1L);


    }
    @Test
    void getAdmin_ValidId_NotMatchingLoggedIdAdmin(){
        AdminEntity admin = AdminEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .build();
        when(requestAccessToken.getAdminId()).thenReturn(2L);
        assertThrows(UnauthorizedDataAccessException.class, () -> getAdminUseCase.getAdmin(1L));
        verify(requestAccessToken).getAdminId();
        verifyNoMoreInteractions(administrationRepositoryMock);

    }

}