package nl.fontys.s3.museumticket.business.impl.museum;

import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetMuseumUseCaseImplTest {
    @Mock
    private MuseumRepository museumRepositoryMock;
    @InjectMocks
    private GetMuseumUseCaseImpl getMuseumUseCase;
    @Test
    void getMuseumById(){
        MuseumEntity bel = MuseumEntity.builder().id(1L).name("bel").location("123 Amsterdam").phone("0123456789").build();
        when(museumRepositoryMock.findById(1L)).thenReturn(Optional.of(bel));
        Optional<Museum> actualResult = getMuseumUseCase.getMuseumById(1L);
        Museum bel1 = Museum.builder().id(1L).name("bel").location("123 Amsterdam").phone("0123456789").build();
        Optional<Museum> expectResult = Optional.of(Museum.builder()
                .id(bel1.getId())
                .name(bel1.getName())
                .location(bel1.getLocation())
                .phone(bel1.getPhone())
                .description(bel1.getDescription())
                .build());
        assertEquals(expectResult,actualResult);
        verify(museumRepositoryMock).findById(1L);
    }


}