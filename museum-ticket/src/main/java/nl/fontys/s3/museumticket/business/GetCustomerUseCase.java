package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.customer.Customer;

import java.util.Optional;

public interface GetCustomerUseCase {
    Optional<Customer> getCustomer(long customerId);
}
