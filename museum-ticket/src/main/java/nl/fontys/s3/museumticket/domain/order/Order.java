package nl.fontys.s3.museumticket.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.museumticket.domain.OrderItem.OrderItem;
import nl.fontys.s3.museumticket.domain.customer.Customer;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private LocalDate orderDate;
    private Customer customer;
    private List<OrderItem> orderItemList;
}
