package nl.fontys.s3.museumticket.business.impl.customer;

import nl.fontys.s3.museumticket.business.exception.EmailAlreadyExistsException;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerRequest;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerResponse;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.UserRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import nl.fontys.s3.museumticket.persistence.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseImplTest {
    @Mock
    private CustomerRepository customerRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;
    @Test
    void createCustomer_ValidRequest(){
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .fullName("Thai Thi Ngoc Huyen")
                .email("thai@gmail.com")
                .password("123")
                .build();
        //set new customer
        CustomerEntity newCustomer = new CustomerEntity().builder()
                .fullName(request.getFullName())
                .build();
        UserEntity userEntity = new UserEntity().builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .customer(newCustomer)
                .build();
        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(userEntity)
                        .role(RoleEnum.CUSTOMER)
                        .build()));
        //set saved customer
        CustomerEntity saveCustomer = new CustomerEntity().builder()
                .id(1L)
                .fullName(request.getFullName())
                .build();
        UserEntity userEntity1 = new UserEntity().builder()
                .email(request.getEmail())
                .password("encodedPassword")
                .customer(saveCustomer)
                .build();
        userEntity1.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(userEntity1)
                        .role(RoleEnum.CUSTOMER)
                        .build()));
        when(userRepositoryMock.findByEmail("thai@gmail.com")).thenReturn(null);
        when(customerRepositoryMock.save(newCustomer)).thenReturn(saveCustomer);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        CreateCustomerResponse actual = createCustomerUseCase.createCustomer(request);
        CreateCustomerResponse expect = CreateCustomerResponse.builder().customerId(1L).build();
        assertEquals(actual,expect);
        verify(userRepositoryMock).findByEmail("thai@gmail.com");
        verify(customerRepositoryMock).save(newCustomer);
        verify(passwordEncoder).encode(request.getPassword());

    }
    @Test
    void setCreateCustomer_ExistEmail(){
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .fullName("Thai Thi Ngoc Huyen")
                .email("thai@gmail.com")
                .password("123")
                .build();
        //set saved customer
        CustomerEntity saveCustomer = new CustomerEntity().builder()
                .id(1L)
                .fullName(request.getFullName())
                .build();
        UserEntity userEntity = new UserEntity().builder()
                .email(request.getEmail())
                .password("encodedPassword")
                .customer(saveCustomer)
                .build();
        userEntity.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(userEntity)
                        .role(RoleEnum.CUSTOMER)
                        .build()));
        when(userRepositoryMock.findByEmail("thai@gmail.com")).thenReturn(userEntity);
        assertThrows(EmailAlreadyExistsException.class,() -> createCustomerUseCase.createCustomer(request));
        verify(userRepositoryMock).findByEmail("thai@gmail.com");
        verifyNoMoreInteractions(userRepositoryMock);

    }


}