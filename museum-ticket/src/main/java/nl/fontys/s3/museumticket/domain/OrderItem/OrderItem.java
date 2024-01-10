package nl.fontys.s3.museumticket.domain.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.museumticket.domain.Ticket.Ticket;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long id;

    private Ticket ticket;
    private Integer amount;
    private Double price;


}
