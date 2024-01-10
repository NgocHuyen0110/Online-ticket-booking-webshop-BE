package nl.fontys.s3.museumticket.business.impl.customer;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.museumticket.business.CreateCustomerUseCase;
import nl.fontys.s3.museumticket.business.exception.EmailAlreadyExistsException;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerRequest;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerResponse;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.UserRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import nl.fontys.s3.museumticket.persistence.entity.UserRoleEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        if(userRepository.findByEmail(request.getEmail())!=null){
            throw new EmailAlreadyExistsException();
        }
        CustomerEntity saveCustomer = saveNewCustomer(request);
        saveNewUser(saveCustomer, request.getPassword(), request.getEmail());
        return CreateCustomerResponse.builder()
                .customerId(saveCustomer.getId())
                .build();

    }
    private void saveNewUser(CustomerEntity customerEntity, String password,String email){
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity newUser = UserEntity.builder()
                .email(email)
                .password(encodedPassword)
                .customer(customerEntity)
                .build();
        newUser.setUserRoles(Set.of(
                UserRoleEntity.builder()
                        .user(newUser)
                        .role(RoleEnum.CUSTOMER)
                        .build()));
         userRepository.save(newUser);
    }
    private CustomerEntity saveNewCustomer(CreateCustomerRequest request){
        CustomerEntity customerEntity = CustomerEntity.builder()
                .fullName(request.getFullName())
                .build();
        return customerRepository.save(customerEntity);
    }
}
