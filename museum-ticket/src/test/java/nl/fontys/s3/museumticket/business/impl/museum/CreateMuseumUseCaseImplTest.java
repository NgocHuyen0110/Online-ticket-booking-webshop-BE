package nl.fontys.s3.museumticket.business.impl.museum;

import nl.fontys.s3.museumticket.business.exception.MuseumNameAlreadyExist;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumRequest;
import nl.fontys.s3.museumticket.domain.museum.CreateMuseumResponse;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class CreateMuseumUseCaseImplTest {
    @Mock
    private MuseumRepository museumRepositoryMock;
    @InjectMocks
    private CreateMuseumUseCaseImpl createMuseumUseCase;
    @Test
    void createMuseum_whenNameIsNew_shouldSave() {

        CreateMuseumRequest request = CreateMuseumRequest.builder().name("iii").location("123 Amsterdam").phone("0123456789")
                .description("general").build();
        MuseumEntity newMuseum =MuseumEntity.builder()
                .name(request.getName())
                .location(request.getLocation())
                .phone(request.getPhone())
                .description(request.getDescription())
                .build();
        MuseumEntity savedMuseum = MuseumEntity.builder()
                .id(22L)
                .name(request.getName())
                .location(request.getLocation())
                .phone(request.getPhone())
                .description(request.getDescription())
                .description(request.getDescription())
                .build();
        when(museumRepositoryMock.findMuseumByName("iii")).thenReturn(null);
        when(museumRepositoryMock.save(newMuseum)).thenReturn(savedMuseum);

        CreateMuseumResponse actual = createMuseumUseCase.createMuseum(request);
        CreateMuseumResponse expected = CreateMuseumResponse.builder().museumId(22L).build();
        assertEquals(actual, expected);

        verify(museumRepositoryMock).findMuseumByName("iii");
        verify(museumRepositoryMock).save(newMuseum);
    }
    @Test
    void createMuseum_whenNameIsExist(){
        CreateMuseumRequest request = CreateMuseumRequest.builder().name("iii").location("123 Amsterdam").phone("0123456789").build();
        MuseumEntity savedMuseum = MuseumEntity.builder()
                .id(22L)
                .name(request.getName())
                .location(request.getLocation())
                .phone(request.getPhone())
                .build();
        when(museumRepositoryMock.findMuseumByName("iii")).thenReturn(savedMuseum);

        assertThrows(MuseumNameAlreadyExist.class, () -> createMuseumUseCase.createMuseum(request));

        verify(museumRepositoryMock).findMuseumByName("iii");
        verifyNoMoreInteractions(museumRepositoryMock);


    }

}