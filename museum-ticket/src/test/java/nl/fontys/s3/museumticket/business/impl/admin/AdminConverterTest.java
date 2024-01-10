package nl.fontys.s3.museumticket.business.impl.admin;

import nl.fontys.s3.museumticket.domain.admin.Administration;
import nl.fontys.s3.museumticket.persistence.entity.AdminEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminConverterTest {
    @Test
    void convert_ConvertsAdminEntityToAdministration() {
        AdminEntity adminEntity = mock(AdminEntity.class);
        when(adminEntity.getId()).thenReturn(1L);
        when(adminEntity.getFullName()).thenReturn("John Doe");
        when(adminEntity.getDob()).thenReturn(LocalDate.of(1990, 5, 15));
        when(adminEntity.getAddress()).thenReturn("123 Main St");

        Administration administration = AdminConverter.convert(adminEntity);

        assertEquals(1L, administration.getId());
        assertEquals("John Doe", administration.getFullName());
        assertEquals(LocalDate.of(1990, 5, 15), administration.getDob());
        assertEquals("123 Main St", administration.getAddress());
    }
//test gain
}