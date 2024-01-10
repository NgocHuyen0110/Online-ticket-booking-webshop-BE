package nl.fontys.s3.museumticket.business.impl.museum;

import nl.fontys.s3.museumticket.domain.museum.Museum;
import nl.fontys.s3.museumticket.persistence.entity.MuseumEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MuseumConverterTest {
    @Test
    void convert_ConvertsMuseumEntityToMuseum() {
        MuseumEntity museumEntity = mock(MuseumEntity.class);
        when(museumEntity.getId()).thenReturn(1L);
        when(museumEntity.getName()).thenReturn("Test Museum");
        when(museumEntity.getLocation()).thenReturn("Test Location");
        when(museumEntity.getPhone()).thenReturn("123456789");
        when(museumEntity.getDescription()).thenReturn("Test Description");
        Museum museum = MuseumConverter.convert(museumEntity);

        assertEquals(1L, museum.getId());
        assertEquals("Test Museum", museum.getName());
        assertEquals("Test Location", museum.getLocation());
        assertEquals("123456789", museum.getPhone());
        assertEquals("Test Description", museum.getDescription());
    }

}