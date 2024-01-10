package nl.fontys.s3.museumticket.business.impl.museum;

import nl.fontys.s3.museumticket.business.exception.InvalidMuseumException;
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

@ExtendWith(MockitoExtension.class)
class DeleteMuseumUseCaseImplTest {
    @Mock
    private MuseumRepository museumRepositoryMock;
    @InjectMocks
    private DeleteMuseumUseCaseImpl deleteMuseumUseCase;
    @Test
    void deleteExistMuseum(){
        long museumId = 1L;
        Optional<MuseumEntity> museumEntity = Optional.of(new MuseumEntity());
        when(museumRepositoryMock.findById(museumId)).thenReturn(museumEntity);
        deleteMuseumUseCase.deleteMuseum(museumId);
        verify(museumRepositoryMock).deleteById(museumId);
    }
    @Test
    void deleteNonExistMuseum(){
        long museumId =1L;
        when(museumRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        assertThrows(InvalidMuseumException.class,() -> deleteMuseumUseCase.deleteMuseum(museumId));
        verifyNoMoreInteractions(museumRepositoryMock);
    }

}