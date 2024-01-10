package nl.fontys.s3.museumticket.business.impl.customer;

import lombok.AllArgsConstructor;
import nl.fontys.s3.museumticket.business.GetAllCustomerUseCase;
import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.domain.customer.GetAllCustomerResponse;
import nl.fontys.s3.museumticket.persistence.CustomerRepository;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCustomerUseCaseImpl implements GetAllCustomerUseCase {
//    private CustomerRepository customerRepository;
    private final CustomerRepository customerRepository;
    @Override
    public GetAllCustomerResponse getCustomers() {
//        List<CustomerEntity> results;
//            results = customerRepository.findAll();
//
//
//        final GetAllCustomerResponse response = new GetAllCustomerResponse();
//        List<Customer> customersOTD = results
//                .stream()
//                .map(CustomerConverter::convert)
//                .toList();
//        response.setCustomers(customersOTD);
//
//        return response;
        List<Customer> customers = customerRepository.findAll()
                .stream()
                .map(CustomerConverter::convert)
                .toList();
        return GetAllCustomerResponse.builder().customers(customers).build();
    }
}
