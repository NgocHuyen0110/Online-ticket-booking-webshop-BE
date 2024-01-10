package nl.fontys.s3.museumticket.business.impl.customer;

import nl.fontys.s3.museumticket.business.exception.UnauthorizedDataAccessException;
import nl.fontys.s3.museumticket.domain.AccessToken;
import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;
    @InjectMocks
    private GetCustomerUseCaseImpl getCustomerUseCase;
    @Mock
    private AccessToken requestAccessToken;
    @Test
    void getCustomer_ValidCustomerId_MatchingLoggedInCustomer_ReturnsCustomer(){
        CustomerEntity customer = CustomerEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .build();
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getCustomerId()).thenReturn(1L);
        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> actual = getCustomerUseCase.getCustomer(1L);
        //1st way is created another Customer Entity and pass it in expect
        Optional<Customer> expect = Optional.of(Customer.builder()
                .id(customer.getId())
                .fullName(customer.getFullName()).build());
        assertEquals(actual,expect);
        verify(requestAccessToken).hasRole(RoleEnum.ADMIN.name());
        verify(requestAccessToken).getCustomerId();
        verify(customerRepositoryMock).findById(1L);
    }
    @Test
    void getCustomer_ValidCustomerId_NotMatchingLoggedInCustomer_ReturnsCustomer(){
        CustomerEntity customer = CustomerEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .build();
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getCustomerId()).thenReturn(2L);
        verifyNoMoreInteractions(customerRepositoryMock);
       assertThrows(UnauthorizedDataAccessException.class,()-> getCustomerUseCase.getCustomer(1L));
       verify(requestAccessToken).hasRole(RoleEnum.ADMIN.name());
       verify(requestAccessToken).getCustomerId();
    }

    @Test
    void getCustomer_ValidCustomerId_AdminRole_ReturnsCustomer(){
        CustomerEntity customer = CustomerEntity.builder()
                .id(1L)
                .fullName("Thai Thi Ngoc Huyen")
                .build();
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> actual = getCustomerUseCase.getCustomer(1L);
        Optional<Customer> expect = Optional.of(Customer.builder()
                .id(customer.getId())
                .fullName(customer.getFullName()).build());
        assertEquals(actual,expect);
        verify(requestAccessToken).hasRole(RoleEnum.ADMIN.name());
        verify(customerRepositoryMock).findById(1L);
    }






}