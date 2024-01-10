package nl.fontys.s3.museumticket.domain.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerResponse {
    private Long customerId;
}
