package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.customer.GetAllCustomerResponse;

public interface GetAllCustomerUseCase {
    GetAllCustomerResponse getCustomers();
}
