package nl.fontys.s3.museumticket.business.impl.museum;

import nl.fontys.s3.museumticket.business.exception.InvalidMuseumException;
import nl.fontys.s3.museumticket.business.exception.MuseumNameAlreadyExist;
import nl.fontys.s3.museumticket.domain.museum.UpdateMuseumRequest;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)

class UpdateMuseumUseCaseImplTest {
    @Mock
    private MuseumRepository museumRepositoryMock; @InjectMocks
    private UpdateMuseumUseCaseImpl updateMuseumUseCase;
    @Test
    void updateExistMuseum(){

        UpdateMuseumRequest request = new UpdateMuseumRequest();
        request.setId(1L);
        request.setName("new Name");
        request.setLocation("new Location");
        request.setPhone("new Phone");
        request.setDescription("new Description");

        MuseumEntity existingMuseum = new MuseumEntity();
        existingMuseum.setId(1L);
        existingMuseum.setName("Existing Name");
        existingMuseum.setLocation("Existing Location");
        existingMuseum.setPhone("Existing Phone");
        existingMuseum.setDescription("Existing Description");
        when(museumRepositoryMock.findById(1L)).thenReturn(Optional.of(existingMuseum));
        when(museumRepositoryMock.findMuseumByName("new Name")).thenReturn(null);
        updateMuseumUseCase.updateMuseum(request);
        assertEquals("new Name", existingMuseum.getName());
        assertEquals("new Location", existingMuseum.getLocation());
        assertEquals("new Phone", existingMuseum.getPhone());
        assertEquals("new Description", existingMuseum.getDescription());

    }
    @Test
    void updateNonExistMuseum(){
        UpdateMuseumRequest request = new UpdateMuseumRequest();
        request.setId(1L);
        request.setName("new Name");
        request.setLocation("new Location");
        request.setPhone("new Phone");
        request.setDescription("new Description");
        when(museumRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        assertThrows(InvalidMuseumException.class, () -> updateMuseumUseCase.updateMuseum(request));
        verify(museumRepositoryMock).findById(1L);
        verifyNoMoreInteractions(museumRepositoryMock);
    }
    @Test
    void updateMuseumWithExistName(){
        UpdateMuseumRequest request = new UpdateMuseumRequest();
        request.setId(1L);
        request.setName("new Name");
        request.setLocation("new Location");
        request.setPhone("new Phone");
        request.setDescription("new Description");

        MuseumEntity existingMuseum = new MuseumEntity();
        existingMuseum.setId(1L);
        existingMuseum.setName("Existing Name");
        existingMuseum.setLocation("Existing Location");
        existingMuseum.setPhone("Existing Phone");
        existingMuseum.setDescription("Existing Description");

        MuseumEntity museumWithSameRequestName = new MuseumEntity();
        museumWithSameRequestName.setId(2L);
        museumWithSameRequestName.setName("new Name");
        museumWithSameRequestName.setLocation(" Location");
        museumWithSameRequestName.setPhone(" Phone");
        museumWithSameRequestName.setDescription("new Description");
        when(museumRepositoryMock.findById(1L)).thenReturn(Optional.of(existingMuseum));
        when(museumRepositoryMock.findMuseumByName("new Name")).thenReturn(museumWithSameRequestName);

        assertThrows(MuseumNameAlreadyExist.class, () -> updateMuseumUseCase.updateMuseum(request));
        verify(museumRepositoryMock).findById(1L);
        verifyNoMoreInteractions(museumRepositoryMock);
    }



}