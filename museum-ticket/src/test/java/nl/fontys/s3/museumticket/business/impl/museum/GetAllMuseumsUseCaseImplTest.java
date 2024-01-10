package nl.fontys.s3.museumticket.business.impl.museum;

import nl.fontys.s3.museumticket.domain.museum.GetAllMuseumsResponse;
import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.MuseumRepository;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllMuseumsUseCaseImplTest {
    @Mock
    private MuseumRepository museumRepositoryMock;
    @InjectMocks
    private GetAllMuseumsUseCaseImpl getMuseumsUseCase;
    @Test
    void getMuseumsUseCase(){
        //test
        MuseumEntity bel = MuseumEntity.builder().name("bel").location("123 Amsterdam").phone("0123456789").build();
        MuseumEntity fra = MuseumEntity.builder().name("fra").location("234 Rotterdam").phone("0987654321").build();
        when(museumRepositoryMock.findAll()).thenReturn(List.of(bel, fra));
        //  getMuseumsUseCase = new GetMuseumsUseCaseImpl(museumRepositoryMock);
        GetAllMuseumsResponse actualResult = getMuseumsUseCase.getMuseums();
        Museum bel1 = Museum.builder().name("bel").location("123 Amsterdam").phone("0123456789").build();
        Museum fra1 = Museum.builder().name("fra").location("234 Rotterdam").phone("0987654321").build();
        GetAllMuseumsResponse expectedResult = GetAllMuseumsResponse.builder().museums(List.of(bel1, fra1)).build();
        assertEquals(expectedResult, actualResult);
        verify(museumRepositoryMock).findAll();
    }
    @Test
    void getMuseumsByName_WithMatchingName_ReturnsMatchingMuseums() {
        String name = "bel";
        MuseumEntity bel = MuseumEntity.builder().name("bel").location("123 Amsterdam").phone("0123456789").build();
        MuseumEntity bel2 = MuseumEntity.builder().name("belgium").location("456 Brussels").phone("9876543210").build();
        when(museumRepositoryMock.findByNameContainingIgnoreCase(name)).thenReturn(List.of(bel, bel2));

        Museum belMuseum = Museum.builder().name("bel").location("123 Amsterdam").phone("0123456789").build();
        Museum belgiumMuseum = Museum.builder().name("belgium").location("456 Brussels").phone("9876543210").build();
        GetAllMuseumsResponse expectedResult = GetAllMuseumsResponse.builder()
                .museums(List.of(belMuseum, belgiumMuseum))
                .build();

        GetAllMuseumsResponse actualResult = getMuseumsUseCase.getMuseumsByName(name);

        assertEquals(expectedResult, actualResult);
        verify(museumRepositoryMock).findByNameContainingIgnoreCase(name);
    }
    @Test
    void getMuseumsByName_WithNoMatchingName_ReturnsEmptyResponse() {
        String name = "abc";
        when(museumRepositoryMock.findByNameContainingIgnoreCase(name)).thenReturn(List.of());

        GetAllMuseumsResponse expectedResult = GetAllMuseumsResponse.builder()
                .museums(List.of())
                .build();
        GetAllMuseumsResponse actualResult = getMuseumsUseCase.getMuseumsByName(name);
        assertEquals(expectedResult, actualResult);
        verify(museumRepositoryMock).findByNameContainingIgnoreCase(name);
    }

}