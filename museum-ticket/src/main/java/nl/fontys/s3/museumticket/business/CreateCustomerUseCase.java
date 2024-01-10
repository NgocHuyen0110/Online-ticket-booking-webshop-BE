package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.customer.CreateCustomerRequest;
import nl.fontys.s3.museumticket.domain.customer.CreateCustomerResponse;

public interface CreateCustomerUseCase {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request);
}
