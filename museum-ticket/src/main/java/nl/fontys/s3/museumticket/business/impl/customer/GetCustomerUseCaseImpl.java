package nl.fontys.s3.museumticket.business.impl.customer;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetCustomerUseCase;
import nl.fontys.s3.museumticket.business.exception.UnauthorizedDataAccessException;
import nl.fontys.s3.museumticket.domain.AccessToken;
import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.entity.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class GetCustomerUseCaseImpl implements GetCustomerUseCase {
    private  CustomerRepository customerRepository;
    private AccessToken requestAccessToken;

    @Override
    public Optional<Customer> getCustomer(long customerId) {
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            if (requestAccessToken.getCustomerId() != customerId) {
                throw new UnauthorizedDataAccessException("CUSTOMER_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }

        return customerRepository.findById(customerId).map(CustomerConverter::convert);
    }
}
