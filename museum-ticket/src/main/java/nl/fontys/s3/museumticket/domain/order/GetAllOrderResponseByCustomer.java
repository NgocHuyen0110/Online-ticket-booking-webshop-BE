package nl.fontys.s3.museumticket.domain.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class GetAllOrderResponseByCustomer {

    private List<Order> orders;
}
