package nl.fontys.s3.museumticket.business.impl.admin;

import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.domain.admin.GetAllAdminResponse;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllAdminUseCaseImplTest {
    @Mock
    private AdministrationRepository administrationRepositoryMock;
    @InjectMocks
    private GetAllAdminUseCaseImpl getAllAdminUseCase;
    @Test
    void getAllAdmin(){
        AdminEntity huyen = AdminEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .build();
        AdminEntity roxana = AdminEntity.builder()
                .id(1L)
                .fullName("Roxana")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .build();
        when(administrationRepositoryMock.findAll()).thenReturn(List.of(huyen,roxana));
        GetAllAdminResponse actual = getAllAdminUseCase.getAdmins();
        Administration huyen1 = Administration.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .build();
        Administration roxana1 = Administration.builder()
                .id(1L)
                .fullName("Roxana")
                .dob(LocalDate.of(2001,10,01))
                .address("Eindhoven")
                .build();
        GetAllAdminResponse expect = GetAllAdminResponse.builder().admins(List.of(huyen1, roxana1)).build();
        assertEquals(actual,expect);
        verify(administrationRepositoryMock).findAll();
    }

}