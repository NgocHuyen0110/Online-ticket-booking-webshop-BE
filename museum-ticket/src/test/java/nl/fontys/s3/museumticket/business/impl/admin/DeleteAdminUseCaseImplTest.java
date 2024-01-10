package nl.fontys.s3.museumticket.business.impl.admin;

import nl.fontys.s3.museumticket.business.exception.InvalidAdminException;
import nl.fontys.s3.museumticket.persistence.AdministrationRepository;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class DeleteAdminUseCaseImplTest {
    @Mock
    private AdministrationRepository administrationRepositoryMock;
    @InjectMocks
    private DeleteAdminUseCaseImpl deleteAdminUseCase;
    @Test
    void deleteExistAdmin(){
        long adminId = 1L;
        Optional<AdminEntity> admin = Optional.of(new AdminEntity());
        when(administrationRepositoryMock.findById(1L)).thenReturn(admin);
        deleteAdminUseCase.deleteAdminById(1L);
        verify(administrationRepositoryMock).deleteById(adminId);
    }
    @Test
    void deleteNonExistAdmin(){
        long adminId = 12L;
        when(administrationRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        assertThrows(InvalidAdminException.class, ()-> deleteAdminUseCase.deleteAdminById(1L));
        verifyNoMoreInteractions(administrationRepositoryMock);
    }
}