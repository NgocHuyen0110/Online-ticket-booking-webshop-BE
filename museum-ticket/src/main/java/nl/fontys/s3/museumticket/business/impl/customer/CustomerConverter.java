package nl.fontys.s3.museumticket.business.impl.customer;

import nl.fontys.s3.museumticket.domain.customer.Customer;
import nl.fontys.s3.museumticket.persistence.entity.CustomerEntity;

public class CustomerConverter {
    private CustomerConverter(){}

    public static Customer convert(CustomerEntity customer){
        return Customer.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .build();
    }
}
