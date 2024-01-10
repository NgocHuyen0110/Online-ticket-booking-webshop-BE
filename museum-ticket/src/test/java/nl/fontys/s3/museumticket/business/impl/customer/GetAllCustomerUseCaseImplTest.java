package nl.fontys.s3.museumticket.business.impl.customer;

import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.domain.customer.GetAllCustomerResponse;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
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
class GetAllCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;
    @InjectMocks
    private GetAllCustomerUseCaseImpl getAllCustomerUseCases;
    @Test
    void getCustomers(){
        CustomerEntity huyen = CustomerEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .build();
        CustomerEntity roxana = CustomerEntity.builder()
                .id(1L)
                .fullName("Roxana Thai")
                .build();
        when(customerRepositoryMock.findAll()).thenReturn(List.of(huyen,roxana));
        GetAllCustomerResponse actual = getAllCustomerUseCases.getCustomers();
        Customer huyen1 = Customer.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .build();
        Customer roxana1 = Customer.builder()
                .id(1L)
                .fullName("Roxana Thai")
                .build();
        GetAllCustomerResponse expect = GetAllCustomerResponse.builder().customers(List.of(huyen1,roxana1)).build();
        assertEquals(actual,expect);
        verify(customerRepositoryMock).findAll();
    }

}