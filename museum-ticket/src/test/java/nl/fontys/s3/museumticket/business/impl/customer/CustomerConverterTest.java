package nl.fontys.s3.museumticket.business.impl.customer;

import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerConverterTest {
    @Test
    void convert_ConvertsCustomerEntityToCustomer() {
        CustomerEntity customerEntity = mock(CustomerEntity.class);

        when(customerEntity.getId()).thenReturn(1L);
        when(customerEntity.getFullName()).thenReturn("John Doe");
        Customer customer = CustomerConverter.convert(customerEntity);

        assertEquals(1L, customer.getId());
        assertEquals("John Doe", customer.getFullName());
    }

}